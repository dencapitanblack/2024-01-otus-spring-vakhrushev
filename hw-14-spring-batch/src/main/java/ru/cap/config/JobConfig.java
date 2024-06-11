package ru.cap.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import ru.cap.entity.Person;
import ru.cap.processor.CSVProcessor;
import ru.cap.processor.MongoProcessor;
import ru.cap.service.MongoPrepareService;

/**
 * @author dvakhrushev
 */
@SuppressWarnings("unused")
@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    private final ru.cap.processor.CSVProcessor CSVProcessor;

    private final MongoPrepareService mongoPrepareService;

    private final FlatFileItemReader<Person> flatFileItemReader;

    private final CSVProcessor csvProcessor;

    private final JpaItemWriter<Person> jpaItemWriter;

    private final JpaPagingItemReader<Person> jpaPagingItemReader;

    private final MongoProcessor mongoProcessor;

    private final MongoItemWriter mongoItemWriter;


    @Bean
    public Step fromCSVtoDBLoad() {

        return new StepBuilder("firstStep", jobRepository)
                .<Person, Person>chunk(50, platformTransactionManager)
                .reader(flatFileItemReader)
                .processor(csvProcessor)
                .writer(jpaItemWriter)
                .build();
    }

    @Bean
    public Job job() {
        return new JobBuilder("myJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(fromCSVtoDBLoad())
                .next(mongoPrepareStep())
                .next(mongoLoaderDataStep())
                .build();
    }

    @Bean
    public Step mongoPrepareStep() {
        return new StepBuilder("mongoPrepare", jobRepository)
                .tasklet(mongoTaskletAdapter(), platformTransactionManager)
                .build();
    }

    @Bean
    public Step mongoLoaderDataStep() {
        return new StepBuilder("mongoLoader", jobRepository)
                .<Person, ru.cap.entity.mongo.Person>chunk(10, platformTransactionManager)
                .reader(jpaPagingItemReader)
                .processor(mongoProcessor)
                .writer(mongoItemWriter)
                .build();
    }

    @Bean
    public MethodInvokingTaskletAdapter mongoTaskletAdapter() {
        MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
        adapter.setTargetObject(mongoPrepareService);
        adapter.setTargetMethod("prepare");
        return adapter;
    }

}