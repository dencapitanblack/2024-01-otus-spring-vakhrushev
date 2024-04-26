package ru.cap.home.service;

import ru.cap.home.models.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllByBookId(String bookId);

    Comment insert(String comment, int bookId);

    void deleteComment(String commentId);

    Comment update(String comment, String commentId);

    Comment findById(String commentId);

}
