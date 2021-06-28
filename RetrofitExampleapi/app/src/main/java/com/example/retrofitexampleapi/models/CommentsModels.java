package com.example.retrofitexampleapi.models;

public class CommentsModels {
    private String postId,email,id,name,body;

    public CommentsModels(String postId, String email, String id, String name, String body) {
        this.postId = postId;
        this.email = email;
        this.id = id;
        this.name = name;
        this.body = body;
    }

    public CommentsModels() {
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
