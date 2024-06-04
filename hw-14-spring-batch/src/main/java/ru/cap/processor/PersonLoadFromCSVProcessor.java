package ru.cap.processor;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.cap.entity.Item;
import ru.cap.entity.Person;
import ru.cap.entity.PersonSetting;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author dvakhrushev
 */

@Component
public class PersonLoadFromCSVProcessor {

    public Person process(Person person) {
        enrichItemData(person);
        enrichPersonSettingData(person);
        return person;
    }

    private void enrichItemData(Person person) {


        List<Item> personItems = IntStream.range(0, new Random().nextInt(5)).boxed().map(i -> {
            Item item = new Item();
            item.setItemNum(i);
            item.setItemUUID(UUID.randomUUID());
            return item;
        }).toList();

        person.setItemList(personItems);


    }

    private void enrichPersonSettingData(Person person) {

        List<PersonSetting> personSettings = IntStream.range(0, new Random().nextInt(3)).boxed().map(i -> {
            PersonSetting setting = new PersonSetting();
            setting.setSettingOne(new Random().nextBoolean());
            setting.setSettingTwo(new Random().nextBoolean());
            setting.setSettingTree(new Random().nextBoolean());
            return setting;
        }).toList();

        person.setPersonSettings(personSettings);

    }
}
