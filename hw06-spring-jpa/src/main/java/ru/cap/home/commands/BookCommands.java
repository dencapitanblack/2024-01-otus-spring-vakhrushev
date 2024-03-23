package ru.cap.home.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.cap.home.converters.BookConverter;
import ru.cap.home.models.Book;
import ru.cap.home.service.BookService;

import java.util.Set;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookService bookService;

    private final BookConverter bookConverter;


    @ShellMethod(value = "Show all book", key = {"b", "book"})
    public String findAllBook() {

        String bookView = bookService.findAll().stream().map(bookConverter::bookToString)
                .collect(Collectors.joining("," + System.lineSeparator()));

        return bookView.isEmpty() ? "Book not found" : bookView;
    }

    @ShellMethod(value = "Add book", key = {"ab", "addbook"})
    public String addBook(
            @ShellOption(help = "Give the title of book") String title,
            @ShellOption(help = "Give an id of authors with comma (',') separated", value = "author") Set<Long> authorId,
            @ShellOption(help = "Give an id of genres with comma (',') separated", value = "genre") Set<Long> genreId) {


            Book book = bookService.insert(title, authorId, genreId);

        return "Book was added \n" + bookConverter.bookToString(book);

    }

    @ShellMethod(value = "Update book", key = {"ub", "updatebook"})
    public String updateBook(
            @ShellOption(help = "Give the title of book", value = "title") String title,
            @ShellOption(help = "Give an id of book", value = "book") long bookId) {

        bookService.update(bookId, title);

        return "Book was updated!";
    }

    @ShellMethod(value = "Remove book", key = {"rb", "removebook"})
    public String deleteBook(@ShellOption(help = "Give an id of book", value = "book") long bookId) {
        bookService.deleteById(bookId);
        return "Book was deleted";
    }

}
