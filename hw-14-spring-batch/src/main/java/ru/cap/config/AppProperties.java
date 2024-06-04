package ru.cap.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author dvakhrushev
 */

@Getter
@Setter
@Component
@Order(0)
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String csvPath;

    private int bathSize;

}