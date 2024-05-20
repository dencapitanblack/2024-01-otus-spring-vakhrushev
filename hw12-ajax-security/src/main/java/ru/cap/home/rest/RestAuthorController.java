package ru.cap.home.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cap.home.dto.AuthorDto;
import ru.cap.home.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestAuthorController {

    private final AuthorService authorService;

    @GetMapping("/author")
    public List<AuthorDto> getAuthors() {
        return authorService.findAll();
    }

}
