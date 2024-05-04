package ru.cap.home.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.cap.home.dto.AuthorDto;
import ru.cap.home.dto.BookDto;
import ru.cap.home.dto.GenreDto;
import ru.cap.home.models.Comment;
import ru.cap.home.service.AuthorService;
import ru.cap.home.service.BookService;
import ru.cap.home.service.CommentService;
import ru.cap.home.service.GenreService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class RestBookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookService bookService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private CommentService commentService;

    @Test
    public void shouldReturnCorrectCommentsList() throws Exception {

        List<Comment> commentList = List.of(
                new Comment(1, "test_comment_1", null),
                new Comment(2, "test_comment_2", null),
                new Comment(3, "test_comment_3", null));

        when(commentService.findAllByBookId(1L)).thenReturn(commentList);
        MvcResult result = mvc.perform(get("/book/comments/1")).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getContentAsString())
                .contains("test_comment_1")
                .contains("test_comment_2")
                .contains("test_comment_3");

    }

    @Test
    void shouldReturnCorrectBookList() throws Exception {
        List<BookDto> correctResult = List.of(new BookDto(1, "test_title_1",
                new GenreDto(1, "test_genre_1"),
                new AuthorDto(1, "test_author_1")));

        when(bookService.findAll()).thenReturn(correctResult);

        MvcResult result = mvc.perform(get("/")).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getContentAsString())
                .contains("test_title_1")
                .contains("test_genre_1")
                .contains("test_author_1");
    }

    @Test
    void shouldReturnCorrectBookDetails() throws Exception {
        GenreDto genreDto = new GenreDto(1, "test_genre_1");
        AuthorDto authorDto = new AuthorDto(1, "test_author_1");
        BookDto correctResult = new BookDto(1, "test_title_1", genreDto, authorDto);

        when(bookService.findById(1L)).thenReturn(correctResult);

        MvcResult mvcResult = mvc.perform(get("/book/1")).andExpect(status().isOk()).andReturn();
        assertThat(mvcResult.getResponse().getContentAsString())
                .contains("test_title_1");
    }

    @Test
    void saveChangedDetails() throws Exception {

        GenreDto genreDto = new GenreDto(1, "test_genre_1");
        AuthorDto authorDto = new AuthorDto(1, "test_author_1");
        List<BookDto> correctResult = List.of(new BookDto(1, "test_title_1", genreDto, authorDto));

        when(bookService.findAll()).thenReturn(correctResult);

        MockHttpServletRequestBuilder post = post("/book/1");
        post.content("id=1&title=Qwe&genre.id=1&author.id=1");
        post.contentType(MediaType.APPLICATION_FORM_URLENCODED);

        mvc.perform(post).andExpect(status().is3xxRedirection());

    }

    @Test
    void deleteBook() throws Exception {
        MockHttpServletRequestBuilder post = post("/book/delete").param("id", "1");
        mvc.perform(post).andExpect(status().is3xxRedirection());
    }

    @Test
    void addBook() throws Exception {
        mvc.perform(get("/book")).andExpect(status().isOk());
    }

    @Test
    void saveNewBook() throws Exception {
        MockHttpServletRequestBuilder post = post("/book");
        post.content("id=1&title=Qwe&genre.id=1&author.id=1");
        post.contentType(MediaType.APPLICATION_FORM_URLENCODED);

        mvc.perform(post).andExpect(status().is3xxRedirection());
    }
}