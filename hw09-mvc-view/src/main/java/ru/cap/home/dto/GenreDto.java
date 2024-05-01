package ru.cap.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.cap.home.models.Genre;

@Data
@AllArgsConstructor
public class GenreDto {

    private long id;

    private String genreName;

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getGenreName());
    }

    public static Genre toDomain(GenreDto genreDto) {
        return new Genre(genreDto.id, genreDto.genreName);
    }
}
