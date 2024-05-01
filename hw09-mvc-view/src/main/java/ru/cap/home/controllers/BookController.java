package ru.cap.home.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.cap.home.service.AuthorService;
import ru.cap.home.service.BookService;
import ru.cap.home.service.GenreService;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final GenreService genreService;

    private final AuthorService authorService;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "index";
    }

    @GetMapping("/book/details/{id}")
    public String viewBookDetails(@PathVariable long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "book/details";
    }

}
