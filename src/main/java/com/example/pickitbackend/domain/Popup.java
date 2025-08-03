package com.example.pickitbackend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자 접근 제한을 protected로 설정하여 외부에서 직접 생성하지 못하도록 함
public class Popup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 얘는 사용하려면 엑셀 파일에서 팝업번호 데이터를 지워야겠구나. ID 생성 책임을 DBMS로 위임하는 내용임 ~
    private Long popupId;

    private String title;
    private String address;
    private String fullAddress;
    private String date;

    @Column(columnDefinition = "TEXT") // TEXT 타입으로 설정하여 긴 문자열을 저장할 수 있도록 함
    private String comment;

    @Column(columnDefinition = "TEXT")
    private String eventInfo;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "popup_hashtags", joinColumns = @JoinColumn(name = "popup_id")) // 해시태그를 저장하기 위한 별도의 테이블 생성. 단순 조회 용이므로 별도의 엔티티로 만들지 않음
    @Column(name = "hashtag")
    private List<String> hashtags;

    @Builder
    private Popup(String title, String address, String fullAddress, String date,
                  String comment, String eventInfo, String contents,
                  String imageUrl, List<String> hashtags) {
        this.title = title;
        this.address = address;
        this.fullAddress = fullAddress;
        this.date = date;
        this.comment = comment;
        this.eventInfo = eventInfo;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.hashtags = hashtags;
    }


}
