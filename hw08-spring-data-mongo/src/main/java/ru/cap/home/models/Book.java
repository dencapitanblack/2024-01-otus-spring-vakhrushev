package ru.cap.home.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;


@Document("book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    private String id;

    @Field
    private String bookName;

    @Field
    private Set<String> authors;

    @Field
    private Set<String> genres;

    @DBRef(lazy = true)
    private List<Comment> comments;

}

