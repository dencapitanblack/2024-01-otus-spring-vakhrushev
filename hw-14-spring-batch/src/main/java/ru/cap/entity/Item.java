package ru.cap.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

/**
 * @author dvakhrushev
 */
@Data
@Entity
@Table(name = "person_items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private UUID itemUUID;

    private int itemNum;

}
