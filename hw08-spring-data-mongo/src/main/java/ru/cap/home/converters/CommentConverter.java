package ru.cap.home.converters;

import org.springframework.stereotype.Component;
import ru.cap.home.models.Comment;

import java.util.List;

@Component
public class CommentConverter {
    public String commentToString(List<Comment> comments) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < comments.size(); i++) {
            sb.append("\n%d. comment - %s, id - %s".formatted(i, comments.get(i).getComment(), comments.get(i).getId()));
        }

        return sb.toString().trim();
    }

}

