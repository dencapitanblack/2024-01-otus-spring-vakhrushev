package ru.cap.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.cap.home.models.Book;

@Data
@AllArgsConstructor
public class BookDto {

    private long id;

    private String title;

    private GenreDto genre;

    private AuthorDto author;

    public static BookDto toDto(Book book) {

        return new BookDto(book.getId(),
                book.getTitle(),
                GenreDto.toDto(book.getGenre()),
                AuthorDto.toDto(book.getAuthor()));
    }

    public static Book toDomain(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(AuthorDto.toDomain(bookDto.getAuthor()));
        book.setGenre(GenreDto.toDomain(bookDto.getGenre()));
        return book;
    }
}
