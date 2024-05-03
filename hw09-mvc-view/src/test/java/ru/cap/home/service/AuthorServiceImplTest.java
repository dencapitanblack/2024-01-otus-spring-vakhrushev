package ru.cap.home.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cap.home.dto.AuthorDto;
import ru.cap.home.models.Author;
import ru.cap.home.repositories.AuthorRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    void findAll() {
        List<Author> authorList = Collections.singletonList(new Author(1, "test_author_1"));
        List<AuthorDto> authorDtoList = authorList.stream().map(AuthorDto::toDto).toList();
        when(authorRepository.findAll()).thenReturn(authorList);
        assertThat(authorService.findAll()).hasSize(1).containsExactlyElementsOf(authorDtoList);
    }

}