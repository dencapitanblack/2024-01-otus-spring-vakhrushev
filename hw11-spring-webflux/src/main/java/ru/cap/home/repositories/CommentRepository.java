package ru.cap.home.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.cap.home.models.Comment;

public interface CommentRepository extends ReactiveCrudRepository<Comment, String> {
    Flux<Comment> findByBookId(String bookId);

}