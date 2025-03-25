package com.example.demo.Controller;

import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.DTO.SettingDTO;
import com.example.demo.Service.Setting.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class SettingController {
    private final SettingService settingService;

    @PostMapping
    public ResponseEntity<ApiResponse<SettingDTO>> createSetting (SettingDTO settingDto){
        SettingDTO createSetting = settingService.createSetting(settingDto);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Create-success",createSetting));
    }
}
