package ru.cap.home.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import ru.cap.home.service.GenreService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(RestGenreController.class)
class RestGenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreService;


    @WithMockUser(username = "admin", roles = "USER")
    @Test
    void testAuthenticatedOnUser() throws Exception {

        when(genreService.findAll()).thenReturn(List.of());
        mockMvc.perform(get("/genre")).andExpect(status().isOk());
    }
}