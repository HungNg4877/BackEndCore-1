package com.example.demo.Entity;

import com.example.demo.Common.PostConstant.MediaType;
import com.example.demo.Common.EntityName.TableName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TableName.MEDIA)
public class Media extends BaseTimeEntity {
    @ManyToOne
    private Post post;
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
    private String mediaUrl;
    private boolean isDelete;

}
