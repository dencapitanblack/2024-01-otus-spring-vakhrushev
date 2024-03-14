package ru.cap.home.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.cap.home.models.Author;
import ru.cap.home.models.Genre;
import ru.cap.home.repositories.mappers.GenreMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@DisplayName("Unit test JdbcGenreRepository")
@ExtendWith(MockitoExtension.class)
class JdbcGenreRepositoryTest {

    @Mock
    private NamedParameterJdbcOperations namedParameterJdbcOperations;
    private GenreRepository genreRepository;


    @BeforeEach
    void setUp() {
        genreRepository = new JdbcGenreRepository(namedParameterJdbcOperations);
    }

    @Test
    void testFindAll() {

        List<Genre> expectedGenre = Arrays.asList(new Genre(1, "Genre_1"), new Genre(2, "Genre_2"));
        when(namedParameterJdbcOperations.query(eq("select * from genre"), ArgumentMatchers.<RowMapper<Genre>>any()))
                .thenReturn(expectedGenre);

        List<Genre> actualGenre = genreRepository.findAll();
        assertEquals(expectedGenre, actualGenre);

    }

    @Test
    void testFindByIdWithRec() {

        List<Genre> genreList = Arrays.asList(new Genre(1, "Genre_1"));

        when(namedParameterJdbcOperations
                .query(eq("select * from genre where id = :id"),  ArgumentMatchers.<Map<String, ?>>any(), ArgumentMatchers.<RowMapper<Genre>>any()))
                .thenReturn(genreList);

        assertEquals(genreRepository.findById(1).get(), genreList.get(0));

    }

    @Test
    void testFindByIdWithoutRec() {

        List<Genre> genreList = new ArrayList<>();

        when(namedParameterJdbcOperations
                .query(eq("select * from genre where id = :id"),  ArgumentMatchers.<Map<String, ?>>any(), ArgumentMatchers.<RowMapper<Genre>>any()))
                .thenReturn(genreList);

        assertEquals(genreRepository.findById(2), Optional.empty());

    }
}