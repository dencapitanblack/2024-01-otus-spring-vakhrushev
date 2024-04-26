package ru.cap.home.repositories;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.cap.home.models.Genre;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
@ExtendWith(SpringExtension.class)
class GenreRepositoryIT {

    @Autowired
    GenreRepository genreRepository;

    @Test
    void findAllByOrderByGenreNameAsc() {

        List<Genre> genreList = genreRepository.findAllByOrderByGenreNameAsc();
        List<String> expected = List.of("Essay", "History", "Novel", "Ode", "Play", "Poem");
        List<String> actual = genreList.stream().map(Genre::getGenreName).toList();

        assertThat(actual).hasSize(expected.size()).containsExactlyElementsOf(expected);

    }

}