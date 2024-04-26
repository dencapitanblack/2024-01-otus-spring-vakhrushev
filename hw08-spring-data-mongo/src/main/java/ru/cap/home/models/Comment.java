package ru.cap.home.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document("comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    private String id;

    private String comment;

    @DBRef
    private Book book;

    public Comment(String comment, Book book) {
        this.comment = comment;
        this.book = book;
    }
}


