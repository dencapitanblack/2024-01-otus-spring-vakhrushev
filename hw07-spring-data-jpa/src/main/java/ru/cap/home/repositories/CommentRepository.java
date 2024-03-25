package ru.cap.home.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.cap.home.models.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByBookId(long id);

}