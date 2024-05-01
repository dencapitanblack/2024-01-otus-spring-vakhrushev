package ru.cap.home.service;

import ru.cap.home.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();

}
