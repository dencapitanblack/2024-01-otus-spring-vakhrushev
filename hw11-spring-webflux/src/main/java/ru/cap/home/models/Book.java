package ru.cap.home.models;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import reactor.core.publisher.Flux;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("book")
public class Book {



    @Id
    private String id;

    @Field
    @NotNull(message = "{empty_title_error}")
    private String title;

    @Field
    private String genre;

    @Field
    private String author;

    @DocumentReference
    private Flux<Comment> comments;

    public Book(String title, String genre, String author, Flux<Comment> comments) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title;
    }


}
