package com.example.demo.Entity;

import com.example.demo.Common.EntityName.TableName;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TableName.PERMISSION)
public class Permission extends BaseTimeEntity{
    private String name;
    private String apiPath;
    private String method;
    private String modules;
    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<RolePermission> rolePermissions;
    private boolean isDelete;
}
