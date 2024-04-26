package com.amplify.backend.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    private String email;
    private String display_name;
    private String external_url;
    private String img_url;
    private String country;
    private String access_token;
    private String refresh_token;
    private String session_id;

    protected User() {};

    public User(String email, String display_name, String external_url, String img_url, String country, String access_token, String refresh_token, String session_id) {
        this.email = email;
        this.display_name = display_name;
        this.external_url = external_url;
        this.img_url = img_url;
        this.country = country;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.session_id = session_id;
    }

    public String getEmail() { return email; }

    public String getDisplayName() { return display_name; }

    public String getExternalUrl() { return external_url; }

    public String getImgUrl() { return img_url; }

    public String getCountry() { return country; }

    public String getAccessToken() { return access_token; }

    public String getRefreshToken() { return refresh_token; }

    public String getSessionId() { return session_id; }

    public void setEmail(String email) { this.email = email; }

    public void setDisplayName(String display_name) { this.display_name = display_name; }

    public void setExternalUrl(String external_url) { this.external_url = external_url; }

    public void setImgUrl(String img_url) { this.img_url = img_url; }

    public void setCountry(String country) { this.country = country; }

    public void setAccessToken(String access_token) { this.access_token = access_token; }

    public void setRefreshToken(String refresh_token) { this.refresh_token = refresh_token; }

    public void setSessionId(String session_id) { this.session_id = session_id; }

    @Override
    public String toString() {
        return "User [email=" + email + 
            ", display_name=" + display_name + 
            ", external_url=" + external_url + 
            ", img_url=" + img_url + 
            ", country=" + country + 
            ", access_token=" + access_token +
            ", refresh_token=" + refresh_token +
            ", session_id=" + session_id + "]";
    }
}
