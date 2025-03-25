package com.example.demo.Entity;

import com.example.demo.Entity.Permission;
import com.example.demo.Entity.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class) // Kích hoạt audit
@IdClass(RolePermissionId.class) // Xác định Composite Key
@Table(name = "role_permission")
public class RolePermission implements Serializable {

    @Id
    @Column(name = "role_id")
    private UUID roleId; // Khóa chính thứ nhất

    @Id
    @Column(name = "permission_id")
    private UUID permissionId; // Khóa chính thứ hai

    // Liên kết với Role
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    // Liên kết với Permission
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", insertable = false, updatable = false)
    private Permission permission;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; // Ngày tạo

    @LastModifiedDate
    private LocalDateTime lastModified; // Ngày cập nhật

    public RolePermission(UUID roleId, UUID permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
}
