package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.Item;
import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelTable;

import java.util.HashMap;
import java.util.Map;

public class AddRebelUseCase {

    public AddRebelUseCase(RebelRepository repository) {
        this.repository = repository;
    }

    private RebelRepository repository;

    public void handle(AddRebelInput input){
        var table = mapToTable(input);
        repository.save(table);
    }

    private static RebelTable mapToTable(AddRebelInput input) {

        Map<Item, Integer> inventory = new HashMap<>();
        inventory.put(Item.WEAPON, 1);
        inventory.put(Item.MUNITION, 1);
        inventory.put(Item.WATER, 1);
        inventory.put(Item.FOOD, 1);

        return RebelTable.builder()
                .name(input.name())
                .age(input.age())
                .gender(input.gender())
                .traitor(input.traitor())
                .latitude(input.latitude())
                .longitude(input.longitude())
                .galaxyName(input.galaxyName())
                .inventory(inventory)
                .build();
    }
}

record AddRebelInput(String name, Integer age, String gender, boolean traitor, Long latitude, Long longitude, String galaxyName, Map<Item, Integer> inventory){}



