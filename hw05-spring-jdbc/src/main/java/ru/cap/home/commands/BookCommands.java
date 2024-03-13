package ru.cap.home.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.cap.home.converters.BookConverter;
import ru.cap.home.models.Book;
import ru.cap.home.services.BookService;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookService bookService;

    private final BookConverter bookConverter;


    @ShellMethod(value = "Show all book", key = {"b", "book"})
    public String findAllBook() {
        return bookService.findAll().stream().map(bookConverter::bookToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Add book", key = {"ab", "addbook"})
    public String addBook(
            @ShellOption(help = "Give the title of book") String title,
            @ShellOption(help = "Give an id of author", value = "author") long authorId,
            @ShellOption(help = "Give an id of genre", value = "genre") long genreId) {

            Book book = bookService.insert(title, authorId, genreId);

        return "Book was added \n" + bookConverter.bookToString(book);

    }

    @ShellMethod(value = "Update book", key = {"ub", "updatebook"})
    public String updateBook(
            @ShellOption(help = "Give the title of book", value = "title") String title,
            @ShellOption(help = "Give an id of book", value = "book") long bookId) {

        Book book = bookService.update(bookId, title);

        return "Book was updated \n" + bookConverter.bookToString(book);
    }

    @ShellMethod(value = "Remove book", key = {"rb", "removebook"})
    public String deleteBook(@ShellOption(help = "Give an id of book", value = "book") long bookId) {
        bookService.deleteById(bookId);
        return "Book was deleted";
    }

}
