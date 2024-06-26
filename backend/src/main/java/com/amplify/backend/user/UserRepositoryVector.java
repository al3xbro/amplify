package com.amplify.backend.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.common.DataType;
import io.milvus.v2.common.IndexParam;
import io.milvus.v2.service.collection.request.AddFieldReq;
import io.milvus.v2.service.collection.request.CreateCollectionReq;
import io.milvus.v2.service.collection.request.HasCollectionReq;
import io.milvus.v2.service.index.request.CreateIndexReq;
import io.milvus.v2.service.vector.request.GetReq;
import io.milvus.v2.service.vector.request.InsertReq;
import io.milvus.v2.service.vector.request.SearchReq;
import io.milvus.v2.service.vector.response.GetResp;
import io.milvus.v2.service.vector.response.SearchResp;

@Repository
public class UserRepositoryVector {

    private final MilvusClientV2 milvusClient;

    @Autowired
    public UserRepositoryVector(MilvusClientV2 milvusClient) {
        this.milvusClient = milvusClient;
        if (!milvusClient.hasCollection(HasCollectionReq.builder()
                .collectionName("users")
                .build())) {
            createUserCollection();
            createIndex();
        }
    }

    public void createUserCollection() {
        CreateCollectionReq.CollectionSchema userCollection = milvusClient.createSchema();
        userCollection.addField(AddFieldReq.builder()
                .fieldName("email")
                .dataType(DataType.VarChar)
                .isPrimaryKey(true)
                .build());
        userCollection.addField(AddFieldReq.builder()
                .fieldName("features")
                .dataType(DataType.FloatVector)
                .dimension(9)
                .build());

        milvusClient.createCollection(CreateCollectionReq.builder()
                .collectionName("users")
                .collectionSchema(userCollection)
                .build());
    }

    public void createIndex() {
        IndexParam indexParam = IndexParam.builder()
                .metricType(IndexParam.MetricType.L2)
                .indexType(IndexParam.IndexType.IVF_SQ8)
                .fieldName("features")
                .build();

        milvusClient.createIndex(CreateIndexReq.builder()
                .collectionName("users")
                .indexParams(Collections.singletonList(indexParam))
                .build());
    }

    public void save(String email, List<Float> vector) {
        // save user to milvus
        List<JSONObject> data = new ArrayList<>();
        JSONObject user = new JSONObject();
        user.put("email", email);
        user.put("features", vector);
        data.add(user);

        milvusClient.insert(InsertReq.builder()
                .collectionName("users")
                .data(data)
                .build());
    }

    public List<String> findByVector(List<Float> vector) {
        // search milvus for similar vectors
        SearchResp res = milvusClient.search(SearchReq.builder()
                .collectionName("users")
                .data(Collections.singletonList(vector))
                .topK(5)
                .build());
        ArrayList<String> userEmails = new ArrayList<>();
        for (SearchResp.SearchResult user : res.getSearchResults().get(0)) {
            userEmails.add(user.getId().toString());
        }
        return userEmails;
    }

    public List<Float> findByEmail(String email) {
        GetResp res = milvusClient.get(GetReq.builder()
                .collectionName("users")
                .ids(Collections.singletonList(email))
                .build());
        return (List<Float>) res.getGetResults().get(0).getEntity().get("features");
    }

}
