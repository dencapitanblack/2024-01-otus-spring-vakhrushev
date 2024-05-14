package ru.cap.home.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.cap.home.dto.BookDto;
import ru.cap.home.dto.CommentDto;
import ru.cap.home.models.Comment;
import ru.cap.home.service.BookService;
import ru.cap.home.service.CommentService;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestBookController.class)
class RestBookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private CommentService commentService;


    private static ObjectWriter om;

    @BeforeAll
    static void setup() {
       om = new ObjectMapper().writer();
    }

    @Test
    void getAllBooks() throws Exception {

        List<BookDto> bookDtoList = List.of(
                new BookDto(1, "book_1", null, null),
                new BookDto(2, "book_2", null, null),
                new BookDto(3, "book_3", null, null));

        when(bookService.findAll()).thenReturn(bookDtoList);

        mvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(bookDtoList)));

    }

    @Test
    void saveBook() throws Exception {
        BookDto bookDto = new BookDto(0, "book_1", null, null);
        when(bookService.update(bookDto)).thenReturn(bookDto);
        mvc.perform(post("/book")
                        .content(om.writeValueAsString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Book saved"));
    }

    @Test
    void commentsList() throws Exception {

        List<Comment> comments = List.of(
                new Comment(1, "Comment_1", null),
                new Comment(2, "Comment_2", null));

        when(commentService.findAllByBookId(1L)).thenReturn(comments);

        mvc.perform(get("/book/1/comment"))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(comments.stream().map(CommentDto::toDto).toList())));

    }

    @Test
    void bookDetails() throws Exception {

        BookDto bookDto = new BookDto(1, "book_1", null, null);

        when(bookService.findById(1L)).thenReturn(bookDto);

        mvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(bookDto)));

    }

    @Test
    void deleteBook() throws Exception {

        doNothing().when(bookService).deleteById(1L);

        mvc.perform(delete("/book/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateBook() throws Exception {

        BookDto bookDto = new BookDto(1, "book_1", null, null);
        when(bookService.update(bookDto)).thenReturn(bookDto);
        mvc.perform(put("/book/1")
                        .content(om.writeValueAsString(bookDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Book updated"));
    }
}