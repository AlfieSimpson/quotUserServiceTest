package com.quot.user.micro.service.test.users.model;

/*

  "metadata": {
    "name": "Nick Melis",
    "email": "nick@quotech.io"
    "role": "CTO"
  }
 */
public class UserMetadata {

    private String name;

    private String email;

    private String role;

    public UserMetadata() {
    }

    public UserMetadata(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
