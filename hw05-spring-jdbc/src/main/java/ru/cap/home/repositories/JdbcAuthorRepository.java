package ru.cap.home.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.cap.home.models.Author;
import ru.cap.home.repositories.mappers.AuthorMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

}
