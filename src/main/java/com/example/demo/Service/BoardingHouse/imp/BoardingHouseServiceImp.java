package com.example.demo.Service.BoardingHouse.imp;

import com.example.demo.Common.Error.ErrorMessage;
import com.example.demo.Common.Setting.SettingKey;
import com.example.demo.DTO.BoardingHouseDTO;
import com.example.demo.Entity.BoardingHouse;
import com.example.demo.Entity.User;
import com.example.demo.Exception.BaseException;
import com.example.demo.Repository.BoardingHouseRepository;
import com.example.demo.Service.BoardingHouse.BoardingHouseService;
import com.example.demo.Service.Setting.SettingService;
import com.example.demo.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class BoardingHouseServiceImp implements BoardingHouseService {
    private final BoardingHouseRepository boardingHouseRepository;
    private final SettingService settingService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BoardingHouseDTO register(BoardingHouseDTO request) {
        User user = userService.getCurrentUser();
        if (isUserRegistered(user)) {
            throw new BaseException(ErrorMessage.FAILED);
        }
        if (isBoardingHouseNameExists(request.getBoardingHouseName())) {
            throw new BaseException(ErrorMessage.FAILED);
        }
        BoardingHouse boardingHouse = BoardingHouse.builder()
                .boardingHouseName(request.getBoardingHouseName())
                .user(user)
                .city(request.getCity())
                .ward(request.getWard())
                .isDelete(false)
                .presentAddress(request.getPresentAddress())
                .build();
        approveBoardingHouseAsync(boardingHouse);
        return modelMapper.map(boardingHouse, BoardingHouseDTO.class);
    }

    private boolean isUserRegistered(User user) {
        return boardingHouseRepository.findByUser(user.getId()).isPresent();
    }
    //Su dung Async thay cho Transactional
    @Transactional
    void approveBoardingHouseAsync(BoardingHouse boardingHouse) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(Integer.parseInt(settingService.getValue(SettingKey.APPROVE_TIME)));  // Simulate delay
                boardingHouseRepository.saveAndFlush(boardingHouse);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
    @Override
    public BoardingHouse getBoardingHouse(String id) {
        return boardingHouseRepository.findBoardingHouseById(UUID.fromString(id));
    }

    private Boolean isBoardingHouseNameExists(String boardingHouseName) {
        return boardingHouseRepository.isBoardingHouseNameExists(boardingHouseName) > 0;
    }
}