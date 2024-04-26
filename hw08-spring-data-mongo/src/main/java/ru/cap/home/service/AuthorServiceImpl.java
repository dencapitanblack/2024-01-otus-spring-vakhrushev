package ru.cap.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cap.home.exceptions.EntityNotFoundException;
import ru.cap.home.models.Author;
import ru.cap.home.repositories.AuthorRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAllByOrderByAuthorNameAsc();
    }

    @Override
    public Set<String> getAuthorsFromId(Set<Integer> set) {


        List<Author> authors = findAll();
        Set<String> retSet = new HashSet<>();

        for (Integer integer : set) {

            if (integer >= authors.size()) {
                throw new EntityNotFoundException("Authors with id %d not found".formatted(integer));
            }

            retSet.add(authors.get(integer).getAuthorName());
        }

        return retSet;
    }
}
