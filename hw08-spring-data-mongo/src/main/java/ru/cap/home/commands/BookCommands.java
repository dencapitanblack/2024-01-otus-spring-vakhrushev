package ru.cap.home.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.cap.home.converters.BookConverter;
import ru.cap.home.models.Book;
import ru.cap.home.service.AuthorService;
import ru.cap.home.service.BookService;
import ru.cap.home.service.GenreService;

import java.util.List;
import java.util.Set;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookService bookService;

    private final BookConverter bookConverter;

    private final AuthorService authorService;

    private final GenreService genreService;


    @ShellMethod(value = "Show all book", key = {"b", "book"})
    public String showAllBook() {

        List<Book> books = bookService.findAll();

        if (books.isEmpty()) {
            return "Books not found";
        }

        return bookConverter.bookToString(books);
    }

    @ShellMethod(value = "Add book", key = {"ab", "addbook"})
    public String addBook(
            @ShellOption(help = "Give the title of book", value = "title") String title,
            @ShellOption(help = "Give an id of authors with comma separated", value = "author") Set<Integer> authorId,
            @ShellOption(help = "Give an id of genres with comma separated", value = "genre") Set<Integer> genreId) {

            Book book = bookService.insert(title,
                    authorService.getAuthorsFromId(authorId),
                    genreService.getGenresFromId(genreId));

        return "Book was added!";

    }

    @ShellMethod(value = "Update book", key = {"ub", "updatebook"})
    public String updateBook(
            @ShellOption(help = "Give the title of book", value = "title") String title,
            @ShellOption(help = "Give an id of book", value = "book") int bookId) {

        bookService.update(bookId, title);

        return "Book was updated!";
    }

    @ShellMethod(value = "Remove book", key = {"rb", "removebook"})
    public String deleteBook(@ShellOption(help = "Give an id of book", value = "book") int bookId) {
        bookService.deleteById(bookId);
        return "Book was deleted";
    }

}
