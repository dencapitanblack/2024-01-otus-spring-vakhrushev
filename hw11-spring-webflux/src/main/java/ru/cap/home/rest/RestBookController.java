package ru.cap.home.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

import reactor.core.publisher.Mono;
import ru.cap.home.models.Book;
import ru.cap.home.models.Comment;
import ru.cap.home.repositories.BookRepository;
import ru.cap.home.repositories.CommentRepository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController("restBookController")
@RequiredArgsConstructor
public class RestBookController {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    @GetMapping("/book")
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/book")
    public Mono<ResponseEntity<String>> saveBook(@Valid @RequestBody Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return getErrorResponse(bindingResult).map(responseEntity -> responseEntity);
        }

        return bookRepository.save(book).thenReturn(ResponseEntity.ok("Book was added"));
    }

    @GetMapping("/book/{id}/comment")
    public Flux<Comment> commentsList(@PathVariable String id) {
        return commentRepository.findByBookId(id);
    }

    @GetMapping("/book/{id}")
    public Mono<Book> bookDetails(@PathVariable String id) {
        return bookRepository.findById(id);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable String id) {
        bookRepository.deleteById(id).subscribe();
    }

    @PutMapping("/book/{id}")
    public Mono<ResponseEntity<String>> updateBook(@Valid @RequestBody Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return getErrorResponse(bindingResult).map(responseEntity -> responseEntity);
        }

        return bookRepository.save(book).thenReturn(ResponseEntity.ok("Book was added"));
    }

    private Mono<ResponseEntity> getErrorResponse(BindingResult bindingResult) {
        Map<String, String> err = new HashMap<>();
        bindingResult.getAllErrors().forEach(objectError -> {
            err.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());
        });
        return Mono.just(ResponseEntity.badRequest().body(err));
    }


}
