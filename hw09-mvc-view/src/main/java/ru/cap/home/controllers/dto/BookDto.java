package ru.cap.home.controllers.dto;

import lombok.Getter;
import ru.cap.home.models.Author;
import ru.cap.home.models.Book;

import java.util.stream.Collectors;


@Getter
public class BookDto {

    private long id;

    private String title;

    private String authors;

    public static BookDto toDto(Book book) {

        String authors = book.getAuthors().stream().map(Author::getFullName).collect(Collectors.joining(", "));
        BookDto bookDto = new BookDto();
        bookDto.id = book.getId();
        bookDto.title = book.getTitle();
        bookDto.authors = authors;
        return bookDto;
    }

}
