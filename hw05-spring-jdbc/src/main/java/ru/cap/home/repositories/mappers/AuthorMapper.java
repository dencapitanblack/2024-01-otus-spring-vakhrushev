package ru.cap.home.repositories.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.cap.home.models.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong("id");
        String fullName = rs.getString("fullname");

        return new Author(id, fullName);
    }
}