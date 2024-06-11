package ru.cap.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import ru.cap.entity.Person;
import ru.cap.entity.mongo.Item;
import ru.cap.entity.mongo.PersonSetting;

import java.util.List;

/**
 * @author dvakhrushev
 */

@Component
@RequiredArgsConstructor
public class MongoProcessor implements ItemProcessor<Person, ru.cap.entity.mongo.Person> {

    private final MongoTemplate mongoTemplate;

    @Override
    public ru.cap.entity.mongo.Person process(Person person) {

        ru.cap.entity.mongo.Person mongoPerson = new ru.cap.entity.mongo.Person();

        List<Item> items = person.getItemList().stream().map(item -> {
            Item mongoItem = new Item();
            mongoItem.setItemUUID(item.getItemUUID());
            mongoItem.setItemNum(item.getItemNum());
            mongoTemplate.save(mongoItem);
            return mongoItem;
        }).toList();

        List<PersonSetting> personSettings = person.getPersonSettings().stream().map(setting -> {
            PersonSetting mongoSetting = new PersonSetting();
            mongoSetting.setSettingOne(setting.isSettingOne());
            mongoSetting.setSettingTwo(setting.isSettingTwo());
            mongoSetting.setSettingTree(setting.isSettingTree());
            mongoTemplate.save(mongoSetting);
            return mongoSetting;
        }).toList();

        mongoPerson.setItemList(items);
        mongoPerson.setPersonSettings(personSettings);

        return mongoPerson;
    }
}