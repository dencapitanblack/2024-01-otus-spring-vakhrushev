package ru.cap.home.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cap.home.exceptions.EntityNotFoundException;
import ru.cap.home.models.Comment;
import ru.cap.home.repositories.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private Comment findCommentById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id = %d not found".formatted(id)));
    }

    @Override
    public List<Comment> findAllByBookId(long id) {
        List<Comment> comments = commentRepository.findAllByBookId(id);
        return comments;
    }


    @Override
    @Transactional
    public void deleteComment(long commentId) {
        commentRepository.deleteById(findCommentById(commentId).getId());
    }

    @Override
    @Transactional
    public Comment insert(String comment, long bookId) {
        return null;
    }

    @Override
    @Transactional
    public Comment update(String comment, long commentId) {

        comment = String.join(" ", comment.split(","));

        Comment updComment = findCommentById(commentId);
        updComment.setComment(comment);
        return commentRepository.save(updComment);
    }
}
