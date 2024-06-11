package ru.cap.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import ru.cap.entity.Person;

/**
 * @author dvakhrushev
 */

@Configuration
@RequiredArgsConstructor
public class Readers {

    public static final String INPUT_FILE_NAME = "inputFileName";

    @Bean
    public JpaPagingItemReader<Person> jpaPersonReader(EntityManagerFactory entityManagerFactory) {

        return new JpaPagingItemReaderBuilder<Person>()
                .name("personReaderFromDB")
                .entityManagerFactory(entityManagerFactory)
                .queryString("from Person")
                .build();
    }

    @Bean
    @StepScope
    FlatFileItemReader<Person> csvPersonReader(@Value("#{jobParameters['" + INPUT_FILE_NAME + "']}") String inputFileName) {

        return new FlatFileItemReaderBuilder<Person>()
                .name("personCSVReader")
                .resource(new FileSystemResource(inputFileName))
                .delimited()
                .delimiter(",")
                .names("index", "customerId", "firstName", "lastName", "company", "city", "country", "phone1", "phone2", "email", "subscriptionDate", "website")
                .targetType(Person.class)
                .build();
    }
}