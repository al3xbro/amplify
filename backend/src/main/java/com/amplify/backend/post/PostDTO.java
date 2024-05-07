package com.amplify.backend.post;

public class PostDTO {
    private String spotifyUrl;
    private PostType type;
    private String description;
    private String authorEmail;

    public String getSpotifyUrl() {
        return spotifyUrl;
    }

    public PostType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }
}