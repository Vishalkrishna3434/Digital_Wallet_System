package com.example.wallet.dto;

import java.time.LocalDateTime;

public class UserResponse {
    private Long id;
    private String username;
    private String name;
    private String email;
    private LocalDateTime createdAt;

    public UserResponse(Long id, String username, String name, String email, LocalDateTime createdAt){
        this.id= id;
        this.username=username;
        this.name = name;
        this.email=email;
        this.createdAt = createdAt;
    }

    public UserResponse(){

    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

}
