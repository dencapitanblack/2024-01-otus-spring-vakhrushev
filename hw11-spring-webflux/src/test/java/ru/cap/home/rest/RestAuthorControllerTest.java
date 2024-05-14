package ru.cap.home.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.cap.home.dto.AuthorDto;
import ru.cap.home.service.AuthorService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestAuthorController.class)
class RestAuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

    @Test
    void getAuthors() throws Exception {

        ObjectWriter om = new ObjectMapper().writer();

        List<AuthorDto> authorDtos = List.of(
                new AuthorDto(1, "author_1"),
                new AuthorDto(2, "author_1"));

        when(authorService.findAll()).thenReturn(authorDtos);

        mvc.perform(get("/author"))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(authorDtos)));

    }
}