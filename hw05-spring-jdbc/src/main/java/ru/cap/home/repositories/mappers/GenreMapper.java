package ru.cap.home.repositories.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.cap.home.models.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {

        Genre genre = new Genre();
        genre.setGenreName(rs.getString("genreName"));
        genre.setId(rs.getLong("id"));

        return genre;
    }
}
