// 댓글 작성 요청 DTO
package com.example.pickitbackend.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private Long popupId;
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
