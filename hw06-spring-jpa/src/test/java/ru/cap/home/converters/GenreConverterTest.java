package ru.cap.home.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cap.home.models.Author;
import ru.cap.home.models.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Unit test GenreConverterTest")
class GenreConverterTest {

    private GenreConverter genreConverter;
    private Genre genre;

    @BeforeEach
    void setUp() {
        genreConverter = new GenreConverter();
        genre = new Genre();
    }

    @Test
    void testGenreToString() {

        String expected = "Id: %d, Name: %s".formatted(genre.getId(), genre.getGenreName());
        String actual = genreConverter.genreToString(genre);

        assertEquals(expected, actual);
    }

}