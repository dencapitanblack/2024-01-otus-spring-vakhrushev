package ru.cap.home.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.cap.home.models.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class JdbcAuthorRepository implements AuthorRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public List<Author> findAll() {
        return namedParameterJdbcOperations.query("select id, fullname from author", new AuthorMapper());
    }

    @Override
    public Optional<Author> findById(long id) {

        Map<String, Object> params = Collections.singletonMap("id", id);

        List<Author> authorList = namedParameterJdbcOperations
                    .query("select id, fullname from author where id = :id", params, new AuthorMapper());

        if (authorList.isEmpty())
            return Optional.empty();

        return Optional.ofNullable(authorList.get(0));
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            
            long id = rs.getLong("id");
            String fullName = rs.getString("fullname");

            return new Author(id, fullName);
        }
    }
}
