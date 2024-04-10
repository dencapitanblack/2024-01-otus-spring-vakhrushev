package ru.cap.home.repositories;

import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.cap.home.models.Author;
import ru.cap.home.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
@ExtendWith(SpringExtension.class)
class AuthorRepositoryIT {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void findAllByOrderByAuthorNameAsc() {

        List<Author> genreList = authorRepository.findAllByOrderByAuthorNameAsc();
        List<String> expected = List.of("Lermontov","Lev Tolstoy","Mark Tven","Theodor Draizer");
        List<String> actual = genreList.stream().map(Author::getAuthorName).toList();

        assertThat(actual).hasSize(expected.size()).containsExactlyElementsOf(expected);

    }
}