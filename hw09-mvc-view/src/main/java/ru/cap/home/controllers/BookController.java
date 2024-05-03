package ru.cap.home.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.cap.home.dto.AuthorDto;
import ru.cap.home.dto.BookDto;
import ru.cap.home.dto.GenreDto;
import ru.cap.home.service.AuthorService;
import ru.cap.home.service.BookService;
import ru.cap.home.service.CommentService;
import ru.cap.home.service.GenreService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final GenreService genreService;

    private final AuthorService authorService;

    private final CommentService commentService;

    @ModelAttribute("genres")
    public List<GenreDto> genres() {
        return genreService.findAll();
    }

    @ModelAttribute("authors")
    public List<AuthorDto> authors() {
        return authorService.findAll();
    }

    @GetMapping("/book/comments/{id}")
    public String commentsList(@PathVariable long id, Model model) {
        model.addAttribute("comments", commentService.findAllByBookId(id));
        return "comments/comments";
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "index";
    }

    @GetMapping("/book/{id}")
    public String viewBookDetails(@PathVariable long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "book/details";
    }


    @PostMapping("/book/{id}")
    public String saveChangedDetails(@PathVariable long id,
                                     @Valid @ModelAttribute("book") BookDto bookDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "book/details";
        }

        bookService.update(bookDto);
        return "redirect:/";
    }

    @PostMapping(value = "/book/delete")
    public String deleteBook(@RequestParam long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/book")
    public String addBook(Model model) {
        model.addAttribute("book", BookDto.toEmptyDto());
        return "book/details";
    }

    @PostMapping("/book")
    public String saveNewBook(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/book/details";
        }

        bookService.update(bookDto);
        return "redirect:/";
    }


}
