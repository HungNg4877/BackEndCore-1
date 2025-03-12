package com.example.demo.Entity;

import java.io.Serializable;
import java.util.UUID;

public class RolePermissionId implements Serializable {
    private UUID roleId;
    private UUID permissionId;

    public RolePermissionId() {}

    public RolePermissionId(UUID roleId, UUID permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    // Getters và Setters
}
