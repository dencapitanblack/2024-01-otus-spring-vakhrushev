package ru.cap.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author dvakhrushev
 */

@Data
@Entity
@Table(name = "person_settings")
public class PersonSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean settingOne;

    private boolean settingTwo;

    private boolean settingTree;

}
