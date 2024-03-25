package ru.cap.home.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.cap.home.models.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TestEntityManager em;

    @Test
    void findCommentsByBookId() {

        List<Comment> actualComments = em.getEntityManager()
                .createQuery("select distinct c from Comment c where c.book.id = 1", Comment.class).getResultList();


        assertThat(commentRepository.findCommentsByBookId(1))
                .hasSizeGreaterThanOrEqualTo(4)
                .containsAll(actualComments);

    }
}