package com.example.pickitbackend.controller;


import com.example.pickitbackend.dto.*;
import com.example.pickitbackend.service.PopupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/popups")
@Tag(name = "Popup", description = "팝업 관련 API")
public class PopupController {
    private final PopupService popupService;

    // 팝업 저장 API
    @PostMapping("/save")
    @Operation(summary = "팝업 저장", description = "팝업을 저장합니다.")
    public ResponseEntity<PopupDetailResponseDto> createPopup(@RequestBody PopupRequestDto popupRequestDto) {
        return ResponseEntity.ok(popupService.savePopup(popupRequestDto));
    }

    // 팝업 전체 조회 API
    @GetMapping("/all")
    @Operation(summary = "모든 팝업 조회", description = "저장된 모든 팝업을 조회합니다.")
    public ResponseEntity<List<PopupListResponseDto>> getAllPopups() {
        List<PopupListResponseDto> response = popupService.getAllPopups();
        return ResponseEntity.ok(response);
    }

    // 지역별 팝업 조회 API
    @GetMapping("/address")
    @Operation(summary = "지역별 팝업 조회", description = "특정 지역의 팝업을 조회합니다.")
    public ResponseEntity<List<PopupAddressResponseDto>> getPopupsByAddress(@RequestParam String address) {
        return ResponseEntity.ok(popupService.getPopupsByAddress(address));
    }

    // 팝업 ID로 단일 팝업 조회 API
    @GetMapping("/{id}")
    @Operation(summary = "팝업 ID로 조회", description = "ID로 특정 팝업을 조회합니다.")
    public ResponseEntity<PopupDetailResponseDto> getPopupById(@PathVariable Long id) {
        PopupDetailResponseDto response = popupService.getPopupById(id);
        return ResponseEntity.ok(response);
    }

}