package ru.cap.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
import ru.cap.entity.Person;
import ru.cap.processor.PersonLoadFromCSVProcessor;

/**
 * @author dvakhrushev
 */
@SuppressWarnings("unused")
@Configuration
@RequiredArgsConstructor
public class JobConfig {


    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    private final PersonLoadFromCSVProcessor personLoadFromCSVProcessor;

    public static final String INPUT_FILE_NAME = "inputFileName";

    @Bean
    @StepScope
    FlatFileItemReader<Person> csvReader(@Value("#{jobParameters['" + INPUT_FILE_NAME + "']}") String inputFileName) {

        return new FlatFileItemReaderBuilder<Person>()
                .name("customerCSVReader")
                .resource(new FileSystemResource(inputFileName))
                .delimited()
                .delimiter(",")
                .names("index","customerId","firstName","lastName","company","city","country","phone1","phone2","email","subscriptionDate","website")
                .targetType(Person.class)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Person, Person> processor() {
        return personLoadFromCSVProcessor::process;
    }

    @Bean
    @StepScope
    public JpaItemWriter<Person> jpaItemWriter(EntityManagerFactory entityManagerFactory) {
        JpaItemWriter<Person> personJpaItemWriter = new JpaItemWriter<>();
        personJpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return personJpaItemWriter;
    }

    @Bean
    public Step firstStep(ItemReader<Person> itemReader, ItemProcessor<Person, Person> processor, ItemWriter<Person> itemWriter) {

        return new StepBuilder("firstStep", jobRepository)
                .<Person, Person>chunk(50, platformTransactionManager)
                .reader(itemReader)
                .processor(processor)
                .writer(itemWriter)
                .build();

    }

    @Bean
    public Job job(Step step) {
        return new JobBuilder("myJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
}

