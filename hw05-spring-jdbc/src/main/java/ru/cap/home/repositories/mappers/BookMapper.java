package ru.cap.home.repositories.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.cap.home.models.Author;
import ru.cap.home.models.Book;
import ru.cap.home.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong("id");
        String title = rs.getString("title");
        long authorId = rs.getLong("authorId");
        long genreId = rs.getLong("genreId");
        String genreName = rs.getString("genreName");
        String fullName = rs.getString("fullName");

        Author author = new Author();
        author.setFullName(fullName);
        author.setId(authorId);

        Genre genre = new Genre();
        genre.setGenreName(genreName);
        genre.setId(genreId);

        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setGenre(genre);
        book.setAuthor(author);

        return book;
    }
}
