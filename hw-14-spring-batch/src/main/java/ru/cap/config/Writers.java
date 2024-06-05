package ru.cap.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.cap.entity.Person;

/**
 * @author dvakhrushev
 */

@Configuration
public class Writers {

    @Bean
    @StepScope
    public JpaItemWriter<Person> jpaItemWriter(EntityManagerFactory entityManagerFactory) {
        JpaItemWriter<Person> personJpaItemWriter = new JpaItemWriter<>();
        personJpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return personJpaItemWriter;
    }

    @Bean
    public MongoItemWriter<ru.cap.entity.mongo.Person> mongoItemWriter(MongoTemplate mongoTemplate) {

        MongoItemWriter<ru.cap.entity.mongo.Person> personMongoItemWriter = new MongoItemWriter<>();
        personMongoItemWriter.setTemplate(mongoTemplate);
        personMongoItemWriter.setMode(MongoItemWriter.Mode.INSERT);
        personMongoItemWriter.setCollection("person");
        return personMongoItemWriter;
    }
}