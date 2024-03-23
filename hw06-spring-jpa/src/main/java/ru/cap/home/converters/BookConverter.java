package ru.cap.home.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.cap.home.models.Book;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookConverter {
    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    public String bookToString(Book book) {

        String strAuthorView = book.getAuthors().stream().map(authorConverter::authorToString)
                .collect(Collectors.joining(", "));
        String strGenreView = book.getGenres().stream().map(genreConverter::genreToString)
                .collect(Collectors.joining(", "));

        return "Id: %d, title: %s, author: {%s}, genres: [%s]"
                .formatted(book.getId(), book.getTitle(), strAuthorView, strGenreView);
    }
}