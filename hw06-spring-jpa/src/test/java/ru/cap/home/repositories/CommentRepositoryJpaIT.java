package ru.cap.home.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.cap.home.models.Author;
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

    @Test
    void givenComment_whenFindByBookId_thenSuccess() {

        Author author = new Author("test_author_1");
        Book book = new Book();
        book.setTitle("title");
        book.setAuthors(Arrays.asList(author));

        em.persist(book);

        Comment comment1 = new Comment("comment_1", book);
        Comment comment2 = new Comment("comment_1", book);
        Comment comment3 = new Comment("comment_1", book);
        Comment comment4 = new Comment("comment_1", book);

        em.persist(comment1);
        em.persist(comment2);
        em.persist(comment3);
        em.persist(comment4);

        assertThat(repositoryJpa.findCommentsByBookId(book.getId())).hasSizeGreaterThanOrEqualTo(4)
                .containsAll(Arrays.asList(comment1, comment2, comment3, comment4));

    }
}