package ru.cap.service;

import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import ru.cap.entity.mongo.Item;
import ru.cap.entity.mongo.Person;
import ru.cap.entity.mongo.PersonSetting;

/**
 * @author dvakhrushev
 */

@Component
@RequiredArgsConstructor
public class MongoPrepareService {

    private final MongoTemplate mongoTemplate;

    public void prepare() {

        MongoDatabase mongoDatabase = mongoTemplate.getMongoDatabaseFactory().getMongoDatabase("spring-batch");
        mongoDatabase.drop();

        mongoTemplate.createCollection(Person.class);
        mongoTemplate.createCollection(Item.class);
        mongoTemplate.createCollection(PersonSetting.class);

    }
}