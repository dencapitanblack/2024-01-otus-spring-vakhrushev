package ru.cap.home.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("author")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Author {

    @Id
    private String id;

    @Field
    private String authorName;

}
