package ru.cap.home.repositories;


import ru.cap.home.models.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    List<Comment> findCommentsByBookId(long id);

    Optional<Comment> findCommentById(long id);

    Comment save(Comment comment);

    void deleteCommentById(long id);


}