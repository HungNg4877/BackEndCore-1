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
@Table(name = TableName.ROLE)
public class Role extends BaseTimeEntity  {
    private String name;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<RolePermission> rolePermissions;
    private boolean isDelete;
}
