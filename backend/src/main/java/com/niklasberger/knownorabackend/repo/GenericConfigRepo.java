package com.niklasberger.knownorabackend.repo;

import com.niklasberger.knownorabackend.data.GenericConfigData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GenericConfigRepo extends JpaRepository<GenericConfigData, String> {

    @Query("SELECT g FROM GenericConfigData g WHERE g.key = ?1")
    Optional<GenericConfigData> findByKey(String key);

    public default Void updateValue(String key, String value) {
        Optional<GenericConfigData> configData = findByKey(key);
        if (configData.isPresent()) {
            GenericConfigData data = configData.get();
            data.setValue(value);
            save(data);
        } else {
            save(new GenericConfigData(key, value));
        }
        return null;
    }

}
