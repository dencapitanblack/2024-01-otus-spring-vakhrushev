package ru.cap.home.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("author")
public class Author {

    @Id
    private String id;

    @Field
    private String fullName;

    public Author(String fullName) {
        this.fullName = fullName;
    }

}
