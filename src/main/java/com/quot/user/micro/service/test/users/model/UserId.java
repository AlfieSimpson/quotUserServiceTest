package com.quot.user.micro.service.test.users.model;


import org.apache.commons.lang3.builder.EqualsBuilder;

public class UserId {

    private String userId;
    private String clientId;

    public UserId() {
    }

    public UserId(String userId, String clientId) {
        this.userId = userId;
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o){
        return EqualsBuilder.reflectionEquals(this, o);
    }
}
