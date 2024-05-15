package ru.cap.home.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("comment")
public class Comment {

    @Id
    private String id;

    @Field
    private String comment;

    @DocumentReference
    private Book book;

    public Comment(String comment, Book book) {
        this.comment = comment;
        this.book = book;
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    public String toString() {
        return "id " + id + " comment " + comment;
    }

}


