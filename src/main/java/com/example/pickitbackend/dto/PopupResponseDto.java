package com.example.pickitbackend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

public class PopupResponseDto {
    private Long id;
    private String title;
    private String address;
    private String fullAddress;
    private String date;
    private String comment;
    private String eventInfo;
    private String contents;
    private List<String> hashtags; // List 형태로 변경
    private String imageUrl;

    @Builder
    public PopupResponseDto(Long id, String title, String address, String fullAddress, String date,
                            String comment, String eventInfo, String contents, List<String> hashtags, String imageUrl) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.fullAddress = fullAddress;
        this.date = date;
        this.comment = comment;
        this.eventInfo = eventInfo;
        this.contents = contents;
        this.hashtags = hashtags; // List 형태로 초기화
        this.imageUrl = imageUrl;
    }

}
