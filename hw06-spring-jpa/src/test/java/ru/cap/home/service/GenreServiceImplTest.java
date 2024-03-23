package ru.cap.home.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cap.home.models.Genre;
import ru.cap.home.repositories.GenreRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;

    @Test
    void findAll() {
        List<Genre> genreList = Collections.singletonList(new Genre(1, "test_genre_1"));
        when(genreRepository.findAll()).thenReturn(genreList);
        assertThat(genreService.findAll()).hasSize(1).containsExactlyElementsOf(genreList);
    }

}