package ru.cap.home.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cap.home.models.Author;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit test AuthorConverterTest")
class AuthorConverterTest {

    private AuthorConverter authorConverter;
    private Author author;

    @BeforeEach
    void setUp() {
        authorConverter = new AuthorConverter();
        author = new Author();
    }

    @Test
    void testAuthorToString() {

        String expected = "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
        String actual = authorConverter.authorToString(author);

        assertEquals(expected, actual);
    }

}