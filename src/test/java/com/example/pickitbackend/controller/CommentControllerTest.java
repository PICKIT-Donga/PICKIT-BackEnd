package com.example.pickitbackend.controller;

import com.example.pickitbackend.dto.CommentRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PopupRepository popupRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Popup popup;

    @BeforeEach
    void setup() {
        popup = Popup.builder().title("팝업테스트").description("테스트").build();
        popupRepository.save(popup);
    }

    @Test
    void 댓글_등록_성공() throws Exception {
        CommentRequestDto request = new CommentRequestDto(
                popup.getId(), "테스트 댓글", LocalDateTime.now(),
                "예약이 편해요", "접근성이 좋아요", "상품이 다양해요",
                "대기 없음", "재고 충분", "여유로움"
        );

        mockMvc.perform(post("/comments")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
