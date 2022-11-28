package com.letscode.resistence.domain.rebel;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class RebelTable {

    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private boolean traitor;

    // TODO change to a new class
    private Long latitude;
    private Long longitude;
    private String galaxyName;

    // TODO change to a new class
    private Map<Item, Integer> inventory;
}

