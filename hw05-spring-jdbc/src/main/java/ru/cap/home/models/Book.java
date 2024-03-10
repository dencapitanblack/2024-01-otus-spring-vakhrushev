package ru.cap.home.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private long id;
    private String title;
    private Author author;
    private Genre genre;

}
