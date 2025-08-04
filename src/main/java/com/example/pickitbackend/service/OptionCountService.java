package com.example.pickitbackend.service;

import com.example.pickitbackend.domain.OptionCount;
import com.example.pickitbackend.repository.OptionCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.pickitbackend.domain.Popup;
import com.example.pickitbackend.domain.Comment;
import com.example.pickitbackend.repository.CommentRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionCountService {

    private final OptionCountRepository optionCountRepository;
    private final CommentRepository commentRepository;


    @Transactional
    public void increaseCount(Popup popup, String category, String optionValue) {
        OptionCount optionCount = optionCountRepository
                .findByPopupAndCategoryAndOptionValue(popup, category, optionValue)
                .orElseGet(() -> OptionCount.builder()
                        .popup(popup)
                        .category(category)
                        .optionValue(optionValue)
                        .count(0)
                        .build());

        optionCount.setCount(optionCount.getCount() + 1);
        optionCountRepository.save(optionCount);
    }

    @Transactional
    public List<OptionCount> getOptionCountsByCategory(Popup popup, String category) {
        // 구현 내용
        return optionCountRepository.findByPopupAndCategory(popup, category);
    }

    @Transactional
    public void decreaseCount(Popup popup, String category, String optionValue) {
        OptionCount optionCount = optionCountRepository
                .findByPopupAndCategoryAndOptionValue(popup, category, optionValue)
                .orElse(null);
        if (optionCount != null && optionCount.getCount() > 0) {
            optionCount.setCount(optionCount.getCount() - 1);
            optionCountRepository.save(optionCount);
        }
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        // ↓↓↓ 댓글이 가지고 있었던 각 옵션에 대해 옵션 카운트를 1씩 감소
        this.decreaseCount(comment.getPopup(), "serviceOption", comment.getServiceOption());
        this.decreaseCount(comment.getPopup(), "envOption", comment.getEnvOption());
        this.decreaseCount(comment.getPopup(), "itemOption", comment.getItemOption());
        this.decreaseCount(comment.getPopup(), "waitStatus", comment.getWaitStatus());
        this.decreaseCount(comment.getPopup(), "stockStatus", comment.getStockStatus());
        this.decreaseCount(comment.getPopup(), "crowdedness", comment.getCrowdedness());

        // ↓↓↓ 마지막으로 실제 댓글 삭제 실행
        commentRepository.delete(comment);
    }


}
