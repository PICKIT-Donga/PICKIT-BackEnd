package com.example.pickitbackend.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PopupResponseDto {
    private Long id;
    private String title;
    private String address;
    private String fullAddress;
    private String date;
    private String comment;
    private String eventInfo;
    private String contents;
    private String imageUrl;
    private List<String> hashtags; // List 형태로 변경
}
