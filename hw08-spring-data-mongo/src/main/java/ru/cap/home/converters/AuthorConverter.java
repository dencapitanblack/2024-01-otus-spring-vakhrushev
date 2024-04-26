package ru.cap.home.converters;

import org.springframework.stereotype.Component;
import ru.cap.home.models.Author;

import java.util.List;

@Component
public class AuthorConverter {

    public String authorToString(List<Author> authors) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < authors.size(); i++) {
            sb.append("\n%d. author - %s".formatted(i, authors.get(i).getAuthorName()));
        }

        return sb.toString().trim();
    }
}