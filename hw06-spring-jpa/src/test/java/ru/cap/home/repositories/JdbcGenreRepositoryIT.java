package ru.cap.home.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.cap.home.models.Genre;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jdbc для работы с жанрами ")
@JdbcTest
@Import({JdbcGenreRepository.class})
class JdbcGenreRepositoryIT {

    @Autowired
    private JdbcGenreRepository jdbcGenreRepository;

    private List<Genre> dbGenre;

    @BeforeEach
    void setUp() {
        dbGenre = getDbGenre();
    }

    @DisplayName("должен загружать жанр по id")
    @ParameterizedTest
    @MethodSource("getDbGenre")
    void shouldReturnCorrectGenreById(Genre expectedGenre) {

        var actualGenre = jdbcGenreRepository.findById(expectedGenre.getId());
        assertThat(actualGenre).isPresent().get().isEqualTo(expectedGenre);

    }

    @DisplayName("Должен загружать список всех жанров")
    @Test
    void shouldReturnCorrectGenreList() {

        var actualGenre = jdbcGenreRepository.findAll();
        var expectedGenre = dbGenre;
        assertThat(expectedGenre).containsExactlyElementsOf(actualGenre);
    }


    private static List<Genre> getDbGenre() {
        return IntStream.range(1, 4).boxed().map(id -> new Genre(id, "Genre_" + id)).toList();
    }


}