package ru.cap.home.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.cap.home.models.Author;
import ru.cap.home.repositories.AuthorRepository;

@RestController
@RequiredArgsConstructor
public class RestAuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping("/author")
    public Flux<Author> getAuthors() {
        return authorRepository.findAll();
    }

}
