package com.niklasberger.knownorabackend.data;

import lombok.Data;

import java.util.List;

@Data
public class PublicUserData {

    private String id;
    private String friendlyName;
    private String email;
    private String picture;

    private List<String> classes;

    public PublicUserData(UserData userData) {
        this.id = userData.getId();
        this.friendlyName = userData.getFriendlyName();
        this.email = userData.getEmail();
        this.picture = userData.getPicture();
        this.classes = userData.getClasses();
    }

}
