// 댓글 응답 DTO
package com.example.pickitbackend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String option1;
    private String option2;
    private int option1Count;
    private int option2Count;
    private String content;
    private LocalDateTime visitTime;
    private Long popupId;
}
