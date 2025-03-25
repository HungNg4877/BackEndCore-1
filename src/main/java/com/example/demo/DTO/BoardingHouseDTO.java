package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardingHouseDTO {
    private UUID id;
    private String boardingHouseName;
    private String presentAddress;
    private String ward;
    private String city;
}