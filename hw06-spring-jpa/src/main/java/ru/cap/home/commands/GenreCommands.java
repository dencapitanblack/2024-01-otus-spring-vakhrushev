package ru.cap.home.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.cap.home.converters.GenreConverter;
import ru.cap.home.services.GenreService;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;

    private final GenreConverter genreConverter;

    @ShellMethod(value = "Show all genre", key = {"g", "genre"})
    public String findAllGenre() {
        return genreService.findAll().stream().map(genreConverter::genreToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

}
