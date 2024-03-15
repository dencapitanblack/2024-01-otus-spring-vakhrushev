package ru.cap.home.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.cap.home.models.Genre;
import ru.cap.home.repositories.mappers.GenreMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        List<Genre> genreList = namedParameterJdbcOperations
                .query("select * from genre where id = :id", params, new GenreMapper());

        if (genreList.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(genreList.get(0));
    }

}