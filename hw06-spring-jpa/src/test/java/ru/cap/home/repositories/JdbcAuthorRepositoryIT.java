package ru.cap.home.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.cap.home.models.Author;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jdbc для работы с авторами ")
@JdbcTest
@Import({JdbcAuthorRepository.class})
class JdbcAuthorRepositoryIT {

    @Autowired
    private JdbcAuthorRepository jdbcAuthorRepository;

    private List<Author> dbAuthor;

    @BeforeEach
    void setUp() {
        dbAuthor = getDbAuthor();
    }

    @DisplayName("должен загружать автора по id")
    @ParameterizedTest
    @MethodSource("getDbAuthor")
    void shouldReturnCorrectAuthorById(Author expectedAuthor) {

        var actualAuthor = jdbcAuthorRepository.findById(expectedAuthor.getId());
        assertThat(actualAuthor).isPresent().get().isEqualTo(expectedAuthor);

    }

    @DisplayName("Должен загружать список всех авторов")
    @Test
    void shouldReturnCorrectGenreList() {
        
        var actualGenre = jdbcAuthorRepository.findAll();
        var expectedGenre = dbAuthor;
        assertThat(expectedGenre).containsExactlyElementsOf(actualGenre);
    }


    private static List<Author> getDbAuthor() {
        return IntStream.range(1, 4).boxed().map(id -> new Author(id, "Author_" + id)).toList();
    }


}