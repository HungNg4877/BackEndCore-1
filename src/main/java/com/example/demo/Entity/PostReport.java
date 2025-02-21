package com.example.demo.Entity;

import com.example.demo.Common.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TableName.POST_REPORT)
@EntityListeners(AuditingEntityListener.class)
public class PostReport extends BaseTimeEntity {
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
    private String reason;

}
