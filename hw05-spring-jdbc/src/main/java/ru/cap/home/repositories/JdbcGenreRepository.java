package ru.cap.home.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.cap.home.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcGenreRepository implements GenreRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    @Override
    public List<Genre> findAll() {
        return namedParameterJdbcOperations.query("select * from genre", new GenreMapper());
    }

    @Override
    public Optional<Genre> findById(long id) {

        Map<String, Object> params = Collections.singletonMap("id", id);
        Genre genre = namedParameterJdbcOperations.queryForObject("select * from genre where id = :id", params, new GenreMapper());
        return Optional.ofNullable(genre);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {

            Genre genre = new Genre();
            genre.setGenreName(rs.getString("genreName"));
            genre.setId(rs.getLong("id"));

            return genre;
        }
    }

}