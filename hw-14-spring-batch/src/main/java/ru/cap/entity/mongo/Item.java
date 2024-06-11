package ru.cap.entity.mongo;

import jakarta.persistence.*;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * @author dvakhrushev
 */
@Data
@Document
public class Item {

    @Id
    private String id;

    private UUID itemUUID;

    private int itemNum;

}
