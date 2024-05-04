package ru.cap.home.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.cap.home.dto.BookDto;
import ru.cap.home.dto.CommentDto;
import ru.cap.home.models.Comment;
import ru.cap.home.service.BookService;
import ru.cap.home.service.CommentService;

import java.util.List;

@Slf4j
@RestController("restBookController")
@RequiredArgsConstructor
public class RestBookController {

    private final BookService bookService;

    private final CommentService commentService;

    @GetMapping("/book")
    public List<BookDto> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/book/comments/{id}")
    public List<CommentDto> commentsList(@PathVariable long id) {
        return commentService.findAllByBookId(id).stream().map(CommentDto::toDto).toList();
    }


}
