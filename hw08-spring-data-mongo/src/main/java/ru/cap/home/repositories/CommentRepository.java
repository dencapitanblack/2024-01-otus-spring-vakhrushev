package ru.cap.home.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.cap.home.models.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBookId(String id);

}
