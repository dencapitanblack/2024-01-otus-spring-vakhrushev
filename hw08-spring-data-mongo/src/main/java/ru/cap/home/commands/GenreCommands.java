package ru.cap.home.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.cap.home.converters.GenreConverter;
import ru.cap.home.models.Genre;
import ru.cap.home.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;

    private final GenreConverter genreConverter;

    @ShellMethod(value = "Show all genre", key = {"g", "genre"})
    public String showAllGenre() {

        List<Genre> genreList = genreService.findAll();

        if (genreList.isEmpty()) {
            return "Genres not found";
        }

        return genreConverter.genreToString(genreList);

    }
}