package com.example.pickitbackend.service;

import com.example.pickitbackend.domain.Comment;
import com.example.pickitbackend.dto.CommentRequestDto;
import com.example.pickitbackend.dto.CommentResponseDto;
import com.example.pickitbackend.repository.CommentRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PopupRepository popupRepository;
    private final OptionCountService optionCountService;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto dto) {
        Popup popup = popupRepository.findById(dto.getPopupId())
                .orElseThrow(() -> new RuntimeException("해당 팝업을 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .popup(popup)
                .content(dto.getContent())
                .visitTime(dto.getVisitTime())
                .serviceOption(dto.getServiceOption())
                .envOption(dto.getEnvOption())
                .itemOption(dto.getItemOption())
                .waitStatus(dto.getWaitStatus())
                .stockStatus(dto.getStockStatus())
                .crowdedness(dto.getCrowdedness())
                .build();

        commentRepository.save(comment);

        // 각 카테고리별로 카운트 증가
        optionCountService.increaseCount(popup, "serviceOption", dto.getServiceOption());
        optionCountService.increaseCount(popup, "envOption", dto.getEnvOption());
        optionCountService.increaseCount(popup, "itemOption", dto.getItemOption());
        optionCountService.increaseCount(popup, "waitStatus", dto.getWaitStatus());
        optionCountService.increaseCount(popup, "stockStatus", dto.getStockStatus());
        optionCountService.increaseCount(popup, "crowdedness", dto.getCrowdedness());

        return convertToDto(comment);
    }
    @Transactional
    public List<CommentResponseDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(this::convertToDto)
                .toList();
    }

    // src/main/java/com/example/pickitbackend/service/CommentService.java
    @Transactional(readOnly = true)
    public CommentResponseDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        return convertToDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        comment.setContent(dto.getContent());
        comment.setVisitTime(dto.getVisitTime());
        comment.setServiceOption(dto.getServiceOption());
        comment.setEnvOption(dto.getEnvOption());
        comment.setItemOption(dto.getItemOption());
        comment.setWaitStatus(dto.getWaitStatus());
        comment.setStockStatus(dto.getStockStatus());
        comment.setCrowdedness(dto.getCrowdedness());
        // 필요시 optionCountService 로직 추가
        return convertToDto(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("댓글을 찾을 수 없습니다.");
        }
        commentRepository.deleteById(id);
    }

    // 기타 CRUD 메서드 작성 (생략 가능)

    private CommentResponseDto convertToDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getPopup().getPopupId(),
                comment.getContent(),
                comment.getVisitTime(),
                comment.getServiceOption(),
                comment.getEnvOption(),
                comment.getItemOption(),
                comment.getWaitStatus(),
                comment.getStockStatus(),
                comment.getCrowdedness()
        );
    }
}


