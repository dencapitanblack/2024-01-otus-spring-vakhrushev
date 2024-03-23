package ru.cap.home.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.cap.home.models.Comment;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Comment> findCommentsByBookId(long id) {

        TypedQuery<Comment> query =
        em.createQuery("select distinct c from Comment c where c.book.id = :id", Comment.class);

        query.setParameter("id", id);
        return query.getResultList();

    }

    @Override
    public Optional<Comment> findCommentById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public Comment save(Comment comment) {

        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        }
        return em.merge(comment);
    }

    @Override
    public void deleteCommentById(long id) {
        em.remove(em.find(Comment.class, id));
    }

}
