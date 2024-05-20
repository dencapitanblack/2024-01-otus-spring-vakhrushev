package ru.cap.home.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Genre {

    @Id
    private String id;

    @Field
    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }
}
