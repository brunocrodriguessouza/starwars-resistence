package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.Itemtable.ItemEnum;
import com.letscode.resistence.domain.Itemtable.ItemTable;
import com.letscode.resistence.domain.rebel.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

        var location = LocationTable.builder()
                .latitude(input.latitude())
                .longitude(input.longitude())
                .galaxyName(input.galaxyName())
                .build();

        var rebelTable = RebelTable.builder()
                .name(input.name())
                .age(input.age())
                .gender(input.gender())
                .traitor(false)
                .build();

        List<ItemTable> inventory = new ArrayList<>();
        inventory.add(0, ItemTable.builder().itemEnum(ItemEnum.WEAPON).rebel(rebelTable).quantity(1).build());
        inventory.add(1, ItemTable.builder().itemEnum(ItemEnum.MUNITION).rebel(rebelTable).quantity(1).build());
        inventory.add(2, ItemTable.builder().itemEnum(ItemEnum.WATER).rebel(rebelTable).quantity(1).build());
        inventory.add(3, ItemTable.builder().itemEnum(ItemEnum.FOOD).rebel(rebelTable).quantity(1).build());

        rebelTable.setItems(inventory);

        location.setRebel(rebelTable);
        rebelTable.setLocation(location);

        return rebelTable;
    }
}



