package ru.cap.entity.mongo;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author dvakhrushev
 */
@Document
@Data
public class Person {

    @Id
    private String id;

    private String index;
    private String customerId;
    private String firstName;
    private String lastName;
    private String company;
    private String city;
    private String country;
    private String phone1;
    private String phone2;
    private String email;
    private String subscriptionDate;
    private String website;

    @DBRef
    private List<Item> itemList;

    @DBRef
    private List<PersonSetting> personSettings;
}