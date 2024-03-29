package ru.cap.home.repositories;

import ru.cap.home.models.Comment;

import java.util.Optional;

public interface CommentRepository {

    Optional<Comment> findCommentById(long id);

    Comment save(Comment comment);

    void deleteCommentById(long id);


}