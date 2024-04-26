package ru.cap.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cap.home.exceptions.EntityNotFoundException;
import ru.cap.home.exceptions.FieldRequired;
import ru.cap.home.models.Book;
import ru.cap.home.models.Comment;
import ru.cap.home.repositories.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BookService bookService;

    private final CommentRepository commentRepository;

    @Override
    public Comment findById(String commentId) {

        return commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException("Comment with id - %s not found".formatted(commentId)));
    }

    @Override
    public List<Comment> findAllByBookId(String bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Override
    public Comment insert(String comment, int bookId) {

        comment = comment.replaceAll(",", " ");
        Book book = bookService.getById(bookId);
        return commentRepository.save(new Comment(comment, book));
    }


    @Override
    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment update(String comment, String commentId) {

        Comment retComment = findById(commentId);
        retComment.setComment(comment.replaceAll(",", " "));

        return save(retComment);

    }

    private Comment save(Comment comment) {

        if (comment.getComment().isEmpty()) {
            throw new FieldRequired("Comment field required");
        }

        if (comment.getBook() == null) {
            throw new FieldRequired("Book field required");
        }

        return commentRepository.save(comment);
    }

}
