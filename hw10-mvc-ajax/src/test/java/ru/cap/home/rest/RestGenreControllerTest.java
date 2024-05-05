package ru.cap.home.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.cap.home.dto.GenreDto;
import ru.cap.home.service.GenreService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestGenreController.class)
class RestGenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreService genreService;

    @Test
    void getGenres() throws Exception {

        ObjectWriter om = new ObjectMapper().writer();

        List<GenreDto> genreDtos = List.of(
                new GenreDto(1, "genre_1"),
                new GenreDto(2, "genre_2"));

        when(genreService.findAll()).thenReturn(genreDtos);

        mvc.perform(get("/genre"))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(genreDtos)));

    }
}