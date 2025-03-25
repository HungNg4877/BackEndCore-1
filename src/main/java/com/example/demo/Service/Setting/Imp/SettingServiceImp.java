package com.example.demo.Service.Setting.Imp;


import com.example.demo.Common.Error.ErrorMessage;
import com.example.demo.Common.Setting.SettingKey;
import com.example.demo.DTO.SettingDTO;
import com.example.demo.Entity.Setting;
import com.example.demo.Exception.BaseException;
import com.example.demo.Repository.SettingRepository;
import com.example.demo.Service.Setting.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//
@Service
@RequiredArgsConstructor
public class SettingServiceImp implements SettingService {
    private final SettingRepository settingRepository;

    @Override
    public SettingDTO createSetting(SettingDTO settingRequest) {
        if(isSettingKeyExist(SettingKey.valueOf(settingRequest.getKey()))){
            throw new BaseException(ErrorMessage.FAILED);
        }
        Setting setting = Setting.builder()
                .key(SettingKey.valueOf(settingRequest.getKey()))
                .value(settingRequest.getValue())
                .build();
        setting = settingRepository.saveAndFlush(setting);
        return new SettingDTO(setting.getKey().toString(), setting.getValue());
    }

    @Override
    public SettingDTO updateSetting(SettingDTO settingRequest) {
        SettingKey key = SettingKey.valueOf(settingRequest.getKey());
        Setting setting = getSetting(key);
        setting.setValue(settingRequest.getValue());
        setting = settingRepository.saveAndFlush(setting);
        return new SettingDTO(setting.getKey().toString(), setting.getValue());
    }

    @Override
    public int getMaxReport() {
        return Integer.parseInt(getSetting(SettingKey.MAX_REPORTS).getValue());
    }

    @Override
    public String getValue(SettingKey settingKey) {
        return getSetting(settingKey).getValue();
    }

    private Boolean isSettingKeyExist(SettingKey key) {
        return settingRepository.findByKey(key).isPresent();
    }

    private Setting getSetting(SettingKey key) {
        return settingRepository.findByKey(key)
                .orElseThrow(() -> new BaseException(ErrorMessage.FAILED));
    }
}
