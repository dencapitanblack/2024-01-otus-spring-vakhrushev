package ru.cap.home.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("genre")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Genre {

    @Id
    private String id;

    @Field
    private String genreName;


}
