package ru.cap.home.converters;

import org.springframework.stereotype.Component;
import ru.cap.home.models.Comment;

@Component
public class CommentConverter {
    public String commentToString(Comment comment) {
        return "Id: %d, Comment: %s".formatted(comment.getId(), comment.getComment());
    }
}
