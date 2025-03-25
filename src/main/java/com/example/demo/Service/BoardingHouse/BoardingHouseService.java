package com.example.demo.Service.BoardingHouse;

import com.example.demo.DTO.BoardingHouseDTO;
import com.example.demo.Entity.BoardingHouse;

public interface BoardingHouseService {
    BoardingHouseDTO register(BoardingHouseDTO boardingHouseDto);
    BoardingHouse getBoardingHouse(String boardingHouseId);

}
