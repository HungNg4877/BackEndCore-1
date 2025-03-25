package com.example.demo.Entity;


import com.example.demo.Common.EntityName.TableName;
import com.example.demo.Common.Setting.SettingKey;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TableName.SETTING)
public class Setting extends BaseTimeEntity {
    private SettingKey key;
    private String value;

}
