package com.example.demo.Controller;

import com.example.demo.Common.EndPoint.EndPointConstant;
import com.example.demo.Common.Success.SuccessMessage;
import com.example.demo.DTO.BoardingHouseDTO;
import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.Service.BoardingHouse.BoardingHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = EndPointConstant.BOARDING_HOUSE)
@RequiredArgsConstructor
public class BoardingHouseController {
    private final BoardingHouseService boardingHouseService;
    @PostMapping
    public ResponseEntity<ApiResponse<BoardingHouseDTO>> registerBoardingHouse(@RequestBody BoardingHouseDTO boardingHouseDto) {
        return ResponseEntity.ok().body(ApiResponse.<BoardingHouseDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message(SuccessMessage.REGISTER_SUCCESS.getMessage())
                .data(boardingHouseService.register(boardingHouseDto))
                .build());
    }
}
