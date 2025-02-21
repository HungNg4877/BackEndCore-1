package com.example.demo.Entity;

import com.example.demo.Common.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TableName.LIKE_COMMENT)
public class LikeComment extends BaseTimeEntity {
    @ManyToOne
    private User user;
    @ManyToOne
    private Comment comment;

}
