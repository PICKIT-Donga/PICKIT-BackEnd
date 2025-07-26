package com.example.pickitbackend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;            // 댓글 내용

    private String option1;             // 선택 옵션 (예: '좋아요', '싫어요')
    private String option2;

    private int option1Count;
    private int option2Count;// 옵션 카운트

    private LocalDateTime visitTime;   // 방문 시간

    private Long popupId;

    // 나중에 Popup 엔티티가 만들어지면 ManyToOne 관계 추가할 수 있음
    // 예: @ManyToOne(fetch = FetchType.LAZY)
    //     @JoinColumn(name = "popup_id")
    //     private Popup popup;
}
