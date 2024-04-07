package ru.cap.home.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.cap.home.converters.AuthorConverter;
import ru.cap.home.service.AuthorService;


import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final AuthorService authorService;

    private final AuthorConverter authorConverter;

    @ShellMethod(value = "Show all authors", key = {"a", "author"})
    public String findAllAuthors() {

        return authorService.findAll().stream().map(authorConverter::authorToString)
                .collect(Collectors.joining("," + System.lineSeparator()));

    }

}
