package com.letscode.resistence.domain.rebel;

import com.letscode.resistence.usecase.exception.ItemTableNotFoundException;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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

    private List<ItemTable> inventory;

    public void addItem(ItemTable item) {
        var itemTable = this.inventory.stream()
                .filter(it -> it.getItem().equals(item.getItem()))
                .findFirst()
                .orElseThrow(ItemTableNotFoundException::new);

        itemTable.setQuantity(itemTable.getQuantity() + item.getQuantity());
    }

    public void remove(ItemTable item) {
        var itemTable = this.inventory.stream()
                .filter(it -> it.getItem().equals(item.getItem()))
                .findFirst()
                .orElseThrow(ItemTableNotFoundException::new);

        itemTable.setQuantity(itemTable.getQuantity() - item.getQuantity());
    }
}

