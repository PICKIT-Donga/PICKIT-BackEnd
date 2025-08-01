package com.example.pickitbackend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "popup_id", nullable = false)
    private Popup popup;

    private String content;
    private LocalDateTime visitTime;

    // 후기 카테고리
    private String serviceOption;
    private String envOption;
    private String itemOption;

    // 현재상황 카테고리
    private String waitStatus;
    private String stockStatus;
    private String crowdedness;
}
