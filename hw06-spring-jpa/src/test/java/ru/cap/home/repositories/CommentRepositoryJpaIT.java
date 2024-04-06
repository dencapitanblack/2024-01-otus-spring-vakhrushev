package ru.cap.home.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.cap.home.models.Book;
import ru.cap.home.models.Comment;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaIT {

    @Autowired
    TestEntityManager em;

    @Autowired
    CommentRepositoryJpa repositoryJpa;

    @Test
    void givenNewComment_whenSave_thenSuccess() {

        Comment newComment = new Comment();
        newComment.setComment("test comment");

        Comment insertedComment = repositoryJpa.save(newComment);
        assertThat(em.find(Comment.class, insertedComment.getId())).isEqualTo(newComment);

    }

    @Test
    void givenComment_whenUpdate_thenSuccess() {

        String expected = "test comment upd";

        Comment comment = em.find(Comment.class, 1);
        comment.setComment(expected);
        repositoryJpa.save(comment);

        assertThat(em.find(Comment.class, comment.getId()).getComment()).isEqualTo(expected);

    }

    @Test
    void givenComment_whenDelete_thenSuccess() {

        Comment comment = new Comment();
        comment.setComment("comment");
        em.persist(comment);
        repositoryJpa.deleteCommentById(comment.getId());
        assertThat(em.find(Comment.class, comment.getId())).isNull();

    }

    @Test
    void givenComment_whenFindById_thenSuccess() {

        Comment comment = new Comment();
        comment.setComment("comment");
        em.persist(comment);
        assertThat(repositoryJpa.findCommentById(comment.getId())).contains(comment);

    }
}