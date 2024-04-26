package ru.cap.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cap.home.exceptions.EntityNotFoundException;
import ru.cap.home.models.Genre;
import ru.cap.home.repositories.GenreRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAllByOrderByGenreNameAsc();
    }


    @Override
    public Set<String> getGenresFromId(Set<Integer> set) {


        List<Genre> genres = findAll();
        Set<String> retSet = new HashSet<>();

        for (Integer integer : set) {

            if (integer >= genres.size()) {
                throw new EntityNotFoundException("Genre with id %d not found".formatted(integer));
            }

            retSet.add(genres.get(integer).getGenreName());
        }

        return retSet;
    }
}
