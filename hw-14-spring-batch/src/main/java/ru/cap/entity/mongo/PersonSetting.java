package ru.cap.entity.mongo;

import jakarta.persistence.*;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author dvakhrushev
 */

@Data
@Document
public class PersonSetting {

    @Id
    private String id;

    private boolean settingOne;

    private boolean settingTwo;

    private boolean settingTree;

}
