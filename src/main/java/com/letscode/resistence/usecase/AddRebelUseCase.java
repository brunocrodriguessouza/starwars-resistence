package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.*;

import java.util.ArrayList;
import java.util.List;

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

        List<ItemTable> inventory = new ArrayList<>();
        inventory.add(0, ItemTable.builder().id(1L).item(Item.WEAPON).quantity(1).build());
        inventory.add(1, ItemTable.builder().id(2L).item(Item.MUNITION).quantity(1).build());
        inventory.add(2, ItemTable.builder().id(3L).item(Item.WATER).quantity(1).build());
        inventory.add(3, ItemTable.builder().id(4L).item(Item.FOOD).quantity(1).build());

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

record AddRebelInput(String name, Integer age, String gender, boolean traitor, Long latitude, Long longitude, String galaxyName){}



