package ru.cap.home.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.cap.home.models.Comment;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByBookId(long id);

    Optional<Comment> findCommentById(long id);

    Comment save(Comment comment);

    void deleteCommentById(long id);


}