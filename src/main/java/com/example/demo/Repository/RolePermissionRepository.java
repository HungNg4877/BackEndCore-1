package com.example.demo.Repository;

import com.example.demo.Entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RolePermissionRepository extends JpaRepository<RolePermission,UUID> {
}
