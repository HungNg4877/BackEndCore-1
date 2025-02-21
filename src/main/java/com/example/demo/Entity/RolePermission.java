package com.example.demo.Entity;

import com.example.demo.Common.TableName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TableName.ROLE_PERMISSION)
public class RolePermission implements Serializable {

    @EmbeddedId
    private RolePermissionId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")  // Maps `roleId` in `RolePermissionId` to `role`
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("permissionId")  // Maps `permissionId` in `RolePermissionId` to `permission`
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;
}
