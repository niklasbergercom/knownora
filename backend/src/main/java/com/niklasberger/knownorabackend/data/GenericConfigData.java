package com.niklasberger.knownorabackend.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

@Entity
@Data
public class GenericConfigData {

    @Id
    @Column(name = "config_key")
    String key;
    String value;

    public GenericConfigData() {

    }

    public GenericConfigData(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
