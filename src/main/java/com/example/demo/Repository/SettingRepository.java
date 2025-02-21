package com.example.demo.Repository;

import com.example.demo.Common.SettingKey;
import com.example.demo.Entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SettingRepository extends JpaRepository<Setting, UUID> {
    Optional<Setting> findByKey(SettingKey key);
}
