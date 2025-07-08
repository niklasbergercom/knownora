package com.niklasberger.knownorabackend.data;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String friendlyName;
    private String email;
    private String password;
    private Integer status;
    private String picture;

    @ElementCollection
    private List<String> classes;

    public UserData(String friendlyName, String email, String password, Integer status, String picture, List<String> classes) {
        this.friendlyName = friendlyName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.picture = picture;
        this.classes = classes;
    }

    public UserData() {

    }

}
