package com.amplify.backend.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getRecentPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return postService.getRecentPosts(page, size);
    }

    @GetMapping("/{id}")
    public List<Post> getPostsByAuthorId(@PathVariable String id) {
        return postService.getPostsByAuthorId(id);
    }

    @PostMapping
    public Post createPost(@RequestBody PostDTO postDTO, @RequestHeader(value = "Authorization") String accessToken) {
        return postService.createPost(
            postDTO.getSpotifyUrl(), 
            postDTO.getType(), 
            postDTO.getDescription(),
            postDTO.getAuthorId(), 
            accessToken
        );
    }

    @GetMapping("/recommended/{id}")
    public List<Post> getRecommendedPosts(@PathVariable String id) {
        return postService.getRecommendedPosts(id);
    }
}
