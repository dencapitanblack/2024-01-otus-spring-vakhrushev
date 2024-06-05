package ru.cap.home.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.cap.home.service.BookService;
import ru.cap.home.service.CommentService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestBookController.class)
public class RestBookControllerTest {

    @MockBean
    private BookService bookService;

    @MockBean
    private CommentService commentService;

    @Autowired
    private MockMvc mvc;

    @WithMockUser(username = "admin", roles = "USER")
    @Test
    public void testGetAllBook() throws Exception {
        when(bookService.findAll()).thenReturn(List.of());
        mvc.perform(get("/")).andExpect(status().isOk());
    }

}
