package ru.cap.home.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.cap.home.controllers.dto.BookDto;
import ru.cap.home.service.BookService;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("booksDto", bookService.findAll().stream().map(BookDto::toDto).toList());
        return "index";
    }

    @GetMapping("/details/{id}")
    public String viewBookDetails(@PathVariable long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "bookDetails";
    }

}
