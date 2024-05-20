package ru.cap.home.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cap.home.dto.GenreDto;
import ru.cap.home.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestGenreController {

    private final GenreService genreService;

    @GetMapping("/genre")
    public List<GenreDto> getGenres() {
        return genreService.findAll();
    }
}
