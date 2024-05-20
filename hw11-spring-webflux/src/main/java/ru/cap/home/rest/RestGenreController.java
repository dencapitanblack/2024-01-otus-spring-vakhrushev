package ru.cap.home.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.cap.home.models.Genre;
import ru.cap.home.repositories.GenreRepository;

@RestController
@RequiredArgsConstructor
public class RestGenreController {

    private final GenreRepository genreRepository;

    @GetMapping("/genre")
    public Flux<Genre> getGenres() {
        return genreRepository.findAll();
    }
}
