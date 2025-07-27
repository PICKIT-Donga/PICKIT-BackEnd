package com.example.pickitbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PopupRequestDto {
    private String title;
    private String address;
    private String fullAddress;
    private String date;
    private String comment;
    private String eventInfo;
    private String contents;
    private String hashtag; // "#곤충#전시" 형태
    private String imageUrl;
}