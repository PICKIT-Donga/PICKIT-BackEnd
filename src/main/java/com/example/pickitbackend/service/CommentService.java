package com.example.pickitbackend.service;

import com.example.pickitbackend.domain.Comment;
import com.example.pickitbackend.dto.CommentRequestDto;
import com.example.pickitbackend.dto.CommentResponseDto;
import com.example.pickitbackend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(CommentRequestDto dto) {
        Comment comment = Comment.builder()
                .option1(dto.getOption1())              // option1 선택지 텍스트
                .option2(dto.getOption2())              // option2 선택지 텍스트
                .option1Count(dto.getOption1Count())    // option1 카운트
                .option2Count(dto.getOption2Count())    // option2 카운트
                .content(dto.getContent())              // 댓글 본문
                .visitTime(dto.getVisitTime())
                .popupId(dto.getPopupId())              // 팝업 id
                .build();
        Comment saved = commentRepository.save(comment);

        return new CommentResponseDto(
                saved.getId(),
                saved.getOption1(),
                saved.getOption2(),
                saved.getOption1Count(),
                saved.getOption2Count(),
                saved.getContent(),
                saved.getVisitTime(),
                saved.getPopupId()
        );
    }

    public List<CommentResponseDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(this::convertToDto)
                .toList();
    }

    public CommentResponseDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        return convertToDto(comment);
    }

    private CommentResponseDto convertToDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getOption1(),
                comment.getOption2(),
                comment.getOption1Count(),
                comment.getOption2Count(),
                comment.getContent(),
                comment.getVisitTime(),
                comment.getPopupId()
        );
    }

    public CommentResponseDto updateComment(Long id, CommentRequestDto dto) {
        // 1. 기존 댓글 찾기 (없으면 예외 발생)
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        // 2. 필드 업데이트
        comment.setOption1(dto.getOption1());
        comment.setOption2(dto.getOption2());
        comment.setOption1Count(dto.getOption1Count());
        comment.setOption2Count(dto.getOption2Count());
        comment.setContent(dto.getContent());
        comment.setVisitTime(dto.getVisitTime());
        comment.setPopupId(dto.getPopupId());

        // 3. 저장 (JPA는 변경 감지로 자동 반영됨, or save() 호출)
        Comment saved = commentRepository.save(comment);

        // 4. DTO로 변환하여 반환
        return convertToDto(saved); // 기존에 사용하던 변환 메서드 활용
    }

    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("댓글을 찾을 수 없습니다.");
        }
        commentRepository.deleteById(id);
    }


}

