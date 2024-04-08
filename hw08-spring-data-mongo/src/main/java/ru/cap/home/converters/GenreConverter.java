package ru.cap.home.converters;

import org.springframework.stereotype.Component;
import ru.cap.home.models.Genre;

import java.util.List;

@Component
public class GenreConverter {

    public String genreToString(List<Genre> genres) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < genres.size(); i++) {
            sb.append("\n%d. genre - %s".formatted(i, genres.get(i).getGenreName()));
        }

        return sb.toString().trim();
    }
}