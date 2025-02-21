package com.example.demo.Repository;

import com.example.demo.Entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    @Query("SELECT p FROM Permission p WHERE p.id = :id and p.isDelete != true")
    Optional<Permission> findById(UUID id);
    @Query("SELECT p FROM Permission p WHERE p.name = :name and p.isDelete != true")
    Optional<Permission> findByName(String name);
    @Query("SELECT p FROM Permission p " +
            "JOIN RolePermission rp ON rp.permission.id = p.id " +
            "WHERE rp.role.id = :roleId AND p.isDelete = false")
    Page<Permission> findAllPermssionById(UUID roleId, Pageable pageable);
}
