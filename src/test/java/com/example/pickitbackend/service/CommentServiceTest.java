package com.example.pickitbackend.service;

import com.example.pickitbackend.domain.Popup;
import com.example.pickitbackend.dto.CommentRequestDto;
import com.example.pickitbackend.dto.CommentResponseDto;
import com.example.pickitbackend.repository.PopupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // (test DB 환경 쓸 땐 프로필 명시)
@Transactional
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PopupRepository popupRepository;

    private Popup popup;

    @BeforeEach
    void setup() {
        popup = Popup.builder()
                .title("테스트 팝업")
                .address("서울특별시")
                .fullAddress("서울특별시 종로구 세종대로 1")
                .date("2025-08-01")
                .comment("설명")
                .eventInfo("이벤트")
                .contents("내용")
                .imageUrl("url")
                .hashtags(List.of("#팝업", "#테스트"))
                .build();
        popupRepository.save(popup);
    }

    @Test
    @DisplayName("댓글 생성 및 단건 조회")
    void 댓글_생성_및_조회() {
        CommentRequestDto req = new CommentRequestDto(
                popup.getPopupId(),
                "테스트 댓글입니다",
                LocalDateTime.now(),
                "예약이 편해요",
                "접근성이 좋아요",
                "상품이 다양해요",
                "대기 없음",
                "재고 충분",
                "여유로움"
        );

        CommentResponseDto response = commentService.createComment(req);
        assertThat(response.getPopupId()).isEqualTo(popup.getPopupId());
        assertThat(response.getContent()).isEqualTo("테스트 댓글입니다");
        assertThat(response.getServiceOption()).isEqualTo("예약이 편해요");
        assertThat(response.getWaitStatus()).isEqualTo("대기 없음");

        // 단건 조회
        CommentResponseDto found = commentService.getCommentById(response.getId());
        assertThat(found.getContent()).isEqualTo("테스트 댓글입니다");
    }

    @Test
    @DisplayName("여러 댓글 전체 조회")
    void 전체_댓글_목록_조회() {
        CommentRequestDto req1 = new CommentRequestDto(popup.getPopupId(), "댓글1", LocalDateTime.now(),
                "서비스1", "공간1", "상품1", "대기1", "재고1", "혼잡1");
        CommentRequestDto req2 = new CommentRequestDto(popup.getPopupId(), "댓글2", LocalDateTime.now(),
                "서비스2", "공간2", "상품2", "대기2", "재고2", "혼잡2");

        commentService.createComment(req1);
        commentService.createComment(req2);

        List<CommentResponseDto> all = commentService.getAllComments();
        assertThat(all.size()).isGreaterThanOrEqualTo(2);
    }
}
