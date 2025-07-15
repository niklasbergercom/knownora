package com.niklasberger.knownorabackend.data;

import lombok.Data;

import java.util.List;

@Data
public class PublicUserData {

    private String id;
    private String friendlyName;
    private String email;
    private String picture;
    private Integer role = 0; // 0 = user, 1 = teacher, 2 = admin
    private String grade;

    private List<String> classes;

    public PublicUserData(UserData userData) {
        this.id = userData.getId();
        this.friendlyName = userData.getFriendlyName();
        this.email = userData.getEmail();
        this.picture = userData.getPicture();
        this.classes = userData.getClasses();
        this.role = userData.getRole();
        this.grade = userData.getGrade();
    }

}
