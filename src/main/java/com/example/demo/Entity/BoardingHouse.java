package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.demo.Common.EntityName.TableName.BOARDING_HOUSE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = BOARDING_HOUSE)
public class BoardingHouse extends BaseTimeEntity {
    @ManyToOne
    private User user;
    private String boardingHouseName;
    private String presentAddress;
    private String ward;
    private String city;
    private boolean isDelete;

}