package ru.cap.home.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cap.home.models.Genre;
import ru.cap.home.repositories.GenreRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    private GenreService genreService;

    @BeforeEach
    public void setUp() {
        genreService = new GenreServiceImpl(genreRepository);
    }

    @Test
    void testFindAll() {

        List<Genre> genreList = Arrays.asList(new Genre(1, "Genre_1"));

        when(genreRepository.findAll()).thenReturn(genreList);

        assertThat(genreList).hasSize(1)
                .containsExactlyElementsOf(genreService.findAll())
                .hasSize(1);

    }
}