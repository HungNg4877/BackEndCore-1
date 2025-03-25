package com.example.demo.Service.Setting;
import com.example.demo.Common.Setting.SettingKey;
import com.example.demo.DTO.SettingDTO;


public interface SettingService {
    SettingDTO createSetting(SettingDTO settingRequest);

    SettingDTO updateSetting(SettingDTO settingRequest);

    int getMaxReport();

    String getValue(SettingKey settingKey);
}
