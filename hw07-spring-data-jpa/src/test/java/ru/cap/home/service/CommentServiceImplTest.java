package ru.cap.home.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cap.home.models.Book;
import ru.cap.home.models.Comment;
import ru.cap.home.repositories.CommentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void findAllByBookId() {
        List<Comment> commentList = Arrays.asList(new Comment("comment_1", new Book()), new Comment("comment_1", new Book()));
        when(commentRepository.findCommentsByBookId(1)).thenReturn(commentList);
        assertThat(commentService.findAllByBookId(1)).hasSize(2);
    }

    @Test
    void delete() {

        when(commentRepository.findCommentById(1)).thenReturn(Optional.of(new Comment(1, "test", new Book())));
        commentService.deleteComment(1);
        verify(commentRepository, times(1)).deleteCommentById(1);
    }

}