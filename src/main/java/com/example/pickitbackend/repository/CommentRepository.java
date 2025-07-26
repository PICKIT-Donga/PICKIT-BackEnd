package com.example.pickitbackend.repository;

import com.example.pickitbackend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}
