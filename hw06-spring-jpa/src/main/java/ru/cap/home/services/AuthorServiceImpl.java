package ru.cap.home.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cap.home.models.Author;
import ru.cap.home.repositories.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }


}
