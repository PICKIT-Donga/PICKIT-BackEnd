package com.example.pickitbackend.config;

import com.example.pickitbackend.service.PopupService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final PopupService popupService;

    @Override
    public void run(ApplicationArguments args) {
        popupService.saveAllFromJson();
    }
}
