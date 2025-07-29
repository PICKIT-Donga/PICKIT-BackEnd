package com.example.pickitbackend.service;

import com.example.pickitbackend.dto.*;
import com.example.pickitbackend.domain.Popup;
import com.example.pickitbackend.repository.PopupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PopupService {
    private final PopupRepository popupRepository;

    // 팝업 저장 메서드
    public PopupDetailResponseDto savePopup(PopupRequestDto popupRequestDto) {

        // 해시태그 파싱 로직 -> "#곤충#전시" 형태의 문자열을 List<String> 형태로 변환
        String rawHashtag = popupRequestDto.getHashtag();
        List<String> parsedHashtags = Arrays.stream(rawHashtag.split("#"))
                .filter(s -> !s.isBlank())
                .map(s -> "#" + s.trim())
                .toList();

        Popup popup = Popup.builder()
                .title(popupRequestDto.getTitle())
                .address(popupRequestDto.getAddress())
                .fullAddress(popupRequestDto.getFullAddress())
                .date(popupRequestDto.getDate())
                .comment(popupRequestDto.getComment())
                .eventInfo(popupRequestDto.getEventInfo())
                .contents(popupRequestDto.getContents())
                .imageUrl(popupRequestDto.getImageUrl())
                .hashtags(parsedHashtags)
                .build();

        return toDetailResponseDTO(popupRepository.save(popup));

    }

    // 팝업 전체 조회 메서드
    public List<PopupListResponseDto> getAllPopups() {
        return popupRepository.findAll().stream()
                .map(p -> PopupListResponseDto.builder()
                        .title(p.getTitle())
                        .address(p.getAddress())
                        .date(p.getDate())
                        .imageUrl(p.getImageUrl())
                        .build())
                .toList();
    }

    // 지역별 팝업 조회 메서드
    public List<PopupAddressResponseDto> getPopupsByAddress(String address) {
        return popupRepository.findByAddressContaining(address).stream()
                .map(p -> PopupAddressResponseDto.builder()
                        .title(p.getTitle())
                        .address(p.getAddress())
                        .date(p.getDate())
                        .imageUrl(p.getImageUrl())
                        .build())
                .toList();
    }

    // 팝업 ID로 단일 팝업 조회 메서드
    public PopupDetailResponseDto getPopupById(Long id) {
        Popup popup = popupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("팝업이 존재하지 않습니다. ID: " + id));
        return toDetailResponseDTO(popup);
    }

    private PopupDetailResponseDto toDetailResponseDTO(Popup popup) {
        return PopupDetailResponseDto.builder()
                .id(popup.getPopupId())
                .title(popup.getTitle())
                .fullAddress(popup.getFullAddress())
                .date(popup.getDate())
                .comment(popup.getComment())
                .eventInfo(popup.getEventInfo())
                .contents(popup.getContents())
                .imageUrl(popup.getImageUrl())
                .hashtags(popup.getHashtags())
                .build();

    }




}
