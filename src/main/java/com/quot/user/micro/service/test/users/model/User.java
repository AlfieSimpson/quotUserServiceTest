package com.quot.user.micro.service.test.users.model;

/*
* {
  "id": {
    "clientId": "quotech",
    "userId": "nick"
  },
  "metadata": {
    "name": "Nick Melis",
    "email": "nick@quotech.io"
    "role": "CTO"
  }
}
 */
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
}
