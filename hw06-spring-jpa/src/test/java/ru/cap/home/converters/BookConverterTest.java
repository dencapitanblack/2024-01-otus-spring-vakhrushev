package ru.cap.home.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cap.home.models.Author;
import ru.cap.home.models.Book;
import ru.cap.home.models.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Unit test BookConverterTest")
class BookConverterTest {

    private AuthorConverter authorConverter;
    private GenreConverter genreConverter;
    private BookConverter bookConverter;

    private Author author;
    private Genre genre;
    private Book book;

    @BeforeEach
    void setUp() {
        authorConverter = new AuthorConverter();
        genreConverter = new GenreConverter();
        bookConverter = new BookConverter(authorConverter, genreConverter);
        author = new Author();
        genre = new Genre();
        book = new Book(0L, null, author, genre);
    }

    @Test
    void testBookToString() {

        String expected = "Id: %d, title: %s, author: {%s}, genres: [%s]".formatted(
                book.getId(),
                book.getTitle(),
                authorConverter.authorToString(book.getAuthor()),
                genreConverter.genreToString(book.getGenre()));

        String actual = bookConverter.bookToString(book);

        assertEquals(expected, actual);
    }

}