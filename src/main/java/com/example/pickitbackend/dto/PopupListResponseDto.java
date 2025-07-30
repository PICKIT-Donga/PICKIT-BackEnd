package com.example.pickitbackend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PopupListResponseDto {
    private Long id;
    private String title;
    private String address;
    private String date;
    private String imageUrl;
}
