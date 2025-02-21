package com.example.demo.Entity;

import com.example.demo.Common.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TableName.COMMENT)
public class Comment extends BaseTimeEntity {
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
    @ManyToOne
    private Comment parentComment;
    private String commentText;
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childComments;
    private boolean isHidden;
    private boolean isDelete;
}