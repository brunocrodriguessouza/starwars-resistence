package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        List<ItemTable> items = new ArrayList<>();
        items.add(0, ItemTable.builder().id(1L).item(Item.WEAPON).quantity(1L).build());
        items.add(1, ItemTable.builder().id(1L).item(Item.MUNITION).quantity(1L).build());
        items.add(2, ItemTable.builder().id(1L).item(Item.WATER).quantity(1L).build());
        items.add(3, ItemTable.builder().id(1L).item(Item.FOOD).quantity(1L).build());

        InventoryTable inventory = InventoryTable.builder().rebelId(1L).items(items).build();

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



