package ru.cap.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cap.home.models.Comment;

@Data
@AllArgsConstructor
public class CommentDto {

    private long id;

    private String comment;

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getComment());
    }

}
