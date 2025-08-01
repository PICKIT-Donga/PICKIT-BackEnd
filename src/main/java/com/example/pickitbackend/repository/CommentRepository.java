package com.example.pickitbackend.repository;

import com.example.pickitbackend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPopup_PopupId(Long popupId);
}

