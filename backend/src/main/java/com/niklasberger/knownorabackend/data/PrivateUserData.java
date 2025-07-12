package com.niklasberger.knownorabackend.data;

import lombok.Data;

import java.util.List;

@Data
public class PrivateUserData {

    private String id;
    private String friendlyName;
    private String email;
    private String status;
    private String picture;

    private List<String> classes;

    public PrivateUserData(UserData userData) {
        this.id = userData.getId();
        this.friendlyName = userData.getFriendlyName();
        this.email = userData.getEmail();
        this.status = userData.getStatus().toString();
        this.picture = userData.getPicture();
        this.classes = userData.getClasses();
    }

}
