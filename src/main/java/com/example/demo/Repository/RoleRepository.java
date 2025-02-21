package com.example.demo.Repository;

import com.example.demo.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query("SELECT r FROM Role r WHERE r.name = :name and r.isDelete != true")
    Role findByName(String name);
    @Query("SELECT r FROM Role r WHERE r.id = :id and r.isDelete != true")
    Optional<Role> findById(UUID id);
}
