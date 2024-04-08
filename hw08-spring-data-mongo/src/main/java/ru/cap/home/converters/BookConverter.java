package ru.cap.home.converters;

import org.springframework.stereotype.Component;
import ru.cap.home.models.Book;

import java.util.List;

@Component
public class BookConverter {

    public String bookToString(List<Book> books) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < books.size(); i++) {

            String authorsView = String.join(", ", books.get(i).getAuthors());
            String genresView = String.join(", ", books.get(i).getGenres());

            sb.append("\n%d. name - %s, authors - %s, genres - %s".formatted(i, books.get(i).getBookName(), authorsView, genresView));
        }

        return sb.toString().trim();
    }
}