package com.letscode.resistence.domain.rebel;

import lombok.Builder;

import java.util.List;

@Builder
public class InventoryTable {

    private Long rebelId;
    private List<ItemTable> items;

    public List<ItemTable> getItems() {
        return items;
    }

    public Long getRebelId() {
        return rebelId;
    }
}
