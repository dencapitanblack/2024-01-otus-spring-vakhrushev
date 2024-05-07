package ru.cap.home.service;

import ru.cap.home.models.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllByBookId(long id);

    Comment insert(String comment, long bookId);

    void deleteComment(long commentId);

    Comment update(String comment, long commentId);


}
