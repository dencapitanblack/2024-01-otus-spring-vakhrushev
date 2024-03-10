package ru.cap.home.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.cap.home.exceptions.EntityNotFoundException;
import ru.cap.home.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class JdbcBookRepository implements BookRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Optional<Book> findById(long id) {

        Map<String, Object> params = Collections.singletonMap("id", id);
        Book book = namedParameterJdbcOperations.
                queryForObject("select * from book where id = :id", params, new BookMapper());

        Optional<Book> optionalBook = Optional.ofNullable(book);

        return optionalBook;
    }

    @Override
    public List<Book> findAll() {
        return namedParameterJdbcOperations.query("select * from book", new BookMapper());
    }

    @Override
    public Book save(Book book) {

        if (book.getId() == 0) {
            return insert(book);
        }
        return update(book);
    }

    private Book update(Book book) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", book.getId());
        params.put("title", book.getTitle());
        params.put("authorid", book.getAuthor().getId());
        params.put("genreid", book.getGenre().getId());

        Integer hasRec = namedParameterJdbcOperations
                .queryForObject("select 1 from book where id = :id", params, Integer.class);

        if (hasRec != null && hasRec == 1) {
            namedParameterJdbcOperations
                    .update("update book set title = :title, authorid = :authorid, genreid = :genreid where id = :id", params);
        } else {
            throw new EntityNotFoundException("No record found for update");
        }

        return book;
    }

    private Book insert(Book book) {

        var keyHolder = new GeneratedKeyHolder();

        Map<String, Object> params = new HashMap<>();
        params.put("title", book.getTitle());
        params.put("authorid", book.getAuthor().getId());
        params.put("genreid", book.getGenre().getId());

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);

        namedParameterJdbcOperations.update("insert into book (title, authorid, genreid) values (:title, :authorid, :genreid)",
                sqlParameterSource,
                keyHolder);

        book.setId(keyHolder.getKeyAs(Long.class));

        return book;
    }

    @Override
    public void deleteById(long id) {

        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from book where id = :id", params);

    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

            long id = rs.getLong("id");
            String title = rs.getString("title");

            Book book = new Book();
            book.setId(id);
            book.setTitle(title);

            return book;
        }
    }
}