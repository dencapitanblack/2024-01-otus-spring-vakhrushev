package ru.cap.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cap.home.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}