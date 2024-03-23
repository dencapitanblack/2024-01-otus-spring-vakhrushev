package ru.cap.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cap.home.exceptions.EntityNotFoundException;
import ru.cap.home.models.Book;
import ru.cap.home.models.Comment;
import ru.cap.home.repositories.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookService bookService;

    private Comment findCommentById(long id) {
        return commentRepository.findCommentById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id = %id not found".formatted(id)));
    }

    @Override
    public List<Comment> findAllByBookId(long id) {
        return commentRepository.findCommentsByBookId(id);
    }


    @Override
    public void deleteComment(long commentId) {
        commentRepository.deleteCommentById(commentId);
    }

    @Override
    public Comment insert(String comment, long bookId) {
        Book book = bookService.findById(bookId);
        return commentRepository.save(new Comment(comment, book));
    }

    @Override
    public Comment update(String comment, long commentId) {

        Comment updComment = findCommentById(commentId);
        updComment.setComment(comment);
        return commentRepository.save(updComment);
    }
}
