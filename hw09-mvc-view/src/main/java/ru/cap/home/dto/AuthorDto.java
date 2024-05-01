package ru.cap.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.cap.home.models.Author;

@Data
@AllArgsConstructor
public class AuthorDto {

    private long id;

    private String fullName;

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getFullName());
    }

    public static Author toDomain(AuthorDto authorDto) {
        return new Author(authorDto.id, authorDto.fullName);
    }
}
