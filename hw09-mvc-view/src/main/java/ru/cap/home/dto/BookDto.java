package ru.cap.home.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cap.home.models.Book;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private long id;

    @NotBlank(message = "Title mustn't be empty")
    @Size(min = 2, message = "Size must be more than 2 symbols")
    private String title;

    private GenreDto genre;

    private AuthorDto author;

    public static BookDto toEmptyDto() {
        return new BookDto(0, null, new GenreDto(), new AuthorDto());
    }

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
