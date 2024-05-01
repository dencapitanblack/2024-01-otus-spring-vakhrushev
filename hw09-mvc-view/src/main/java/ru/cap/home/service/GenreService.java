package ru.cap.home.service;

import ru.cap.home.dto.GenreDto;
import ru.cap.home.models.Genre;

import java.util.List;

public interface GenreService {
    List<GenreDto> findAll();

}
