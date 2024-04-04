package ru.cap.home.converters;

import org.springframework.stereotype.Component;
import ru.cap.home.models.Genre;

@Component
public class GenreConverter {
    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getGenreName());
    }
}
