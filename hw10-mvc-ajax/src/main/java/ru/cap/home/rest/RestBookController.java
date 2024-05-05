package ru.cap.home.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.cap.home.dto.BookDto;
import ru.cap.home.dto.CommentDto;
import ru.cap.home.service.BookService;
import ru.cap.home.service.CommentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/book")
    public ResponseEntity<String> saveBook(@Valid @RequestBody BookDto bookDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return getErrorResponse(bindingResult);
        }

        bookService.update(bookDto);
        return ResponseEntity.ok("Book saved");
    }

    @GetMapping("/book/{id}/comment")
    public List<CommentDto> commentsList(@PathVariable long id) {
        return commentService.findAllByBookId(id).stream().map(CommentDto::toDto).toList();
    }

    @GetMapping("/book/{id}")
    public BookDto bookDetails(@PathVariable long id) {
        return bookService.findById(id);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteById(id);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<String> updateBook(@Valid @RequestBody BookDto bookDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return getErrorResponse(bindingResult);
        }

        bookService.update(bookDto);
        return ResponseEntity.ok("Book updated");
    }

    private ResponseEntity getErrorResponse(BindingResult bindingResult) {
        Map<String, String> err = new HashMap<>();
        bindingResult.getAllErrors().forEach(objectError -> {
            err.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(err);
    }


}
