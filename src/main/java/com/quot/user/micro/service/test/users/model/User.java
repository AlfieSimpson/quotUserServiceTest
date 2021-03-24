package com.quot.user.micro.service.test.users.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.util.ReflectionUtils;

public class User {

    private UserId id;
    private UserMetadata metadata;

    public User() {
    }

    public User(UserId id, UserMetadata metadata) {
        this.id = id;
        this.metadata = metadata;
    }

    public User(String clientId, String userId, String name, String email, String role){
        this.id = new UserId(userId, clientId);
        this.metadata = new UserMetadata(name, email, role);
    }

    public UserId getId() {
        return id;
    }

    public void setId(UserId id) {
        this.id = id;
    }

    public void setId(String clientId, String userId){
        this.id = new UserId(userId, clientId);
    }

    public UserMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(UserMetadata metadata) {
        this.metadata = metadata;
    }

    public void setMetadata(String name, String email, String role){
        this.metadata = new UserMetadata(name, email, role);
    }
    @Override
    public boolean equals(Object o){
        return EqualsBuilder.reflectionEquals(this, o);
    }
}
