package ru.cap.home.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.cap.home.service.AuthorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestAuthorController.class)
class RestAuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;


    @Test
    @WithMockUser(roles = "USER")
    void testAuthenticatedOnUser() throws Exception {
        mockMvc.perform(get("/author"))
                .andExpect(status().isOk());
    }

    @Test
    void testAuthenticatedOnUser_withError() throws Exception {
        mockMvc.perform(get("/author"))
                .andExpect(status().isUnauthorized());
    }
}