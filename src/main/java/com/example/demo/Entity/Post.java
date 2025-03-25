package com.example.demo.Entity;

import com.example.demo.Common.EntityName.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
@Table(name = TableName.POST)
public class Post extends BaseTimeEntity {
    @ManyToOne
    private User user;
    private String content;
    @ManyToOne
    private Post parentPost;
    private boolean isDelete;
}
