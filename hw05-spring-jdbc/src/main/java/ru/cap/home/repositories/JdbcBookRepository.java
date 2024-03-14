package ru.cap.home.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.cap.home.exceptions.EntityNotFoundException;
import ru.cap.home.models.Book;
import ru.cap.home.repositories.mappers.BookMapper;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class JdbcBookRepository implements BookRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Optional<Book> findById(long id) {

        Map<String, Object> params = Collections.singletonMap("id", id);

        List<Book> bookList = namedParameterJdbcOperations.
                query("select book.id, book.title, book.authorid, book.genreid, author.fullname, genre.genrename from book " +
                          "left join author on author.id = book.authorid " +
                          "left join genre on genre.id = book.genreid where book.id = :id", params, new BookMapper());

        if (bookList.isEmpty())
            return Optional.empty();

        return Optional.ofNullable(bookList.get(0));
    }

    @Override
    public List<Book> findAll() {
        return namedParameterJdbcOperations.query(
                "select book.id, book.title, book.authorid, book.genreid, author.fullname, genre.genrename from book " +
                    "left join author on author.id = book.authorid " +
                    "left join genre on genre.id = book.genreid", new BookMapper());
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
        params.put("authorId", book.getAuthor().getId());
        params.put("genreId", book.getGenre().getId());

        // TODO hasRec

        Integer hasRec = namedParameterJdbcOperations
                .queryForObject("select 1 from book where id = :id", params, Integer.class);

        if (hasRec != null && hasRec == 1) {
            namedParameterJdbcOperations
                    .update("update book set title = :title, authorid = :authorId, genreid = :genreId where id = :id", params);
        } else {
            throw new EntityNotFoundException("No record found for update");
        }

        return book;
    }

    private Book insert(Book book) {

        var keyHolder = new GeneratedKeyHolder();

        Map<String, Object> params = new HashMap<>();
        params.put("title", book.getTitle());
        params.put("authorId", book.getAuthor().getId());
        params.put("genreId", book.getGenre().getId());

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);

        namedParameterJdbcOperations.update("insert into book (title, authorid, genreid) values (:title, :authorId, :genreId)",
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


}