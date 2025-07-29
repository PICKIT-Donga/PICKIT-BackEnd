package com.example.pickitbackend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PopupAddressResponseDto {
    private Long id;
    private String title;
    private String address;
    private String date;
    private String comment;
    private String imageUrl;
}
