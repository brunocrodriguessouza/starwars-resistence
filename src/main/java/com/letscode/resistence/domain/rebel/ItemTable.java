package com.letscode.resistence.domain.rebel;

import lombok.Builder;

@Builder
public class ItemTable {

    private Long id;
    private Item item;
    private Integer quantity;

    public Item getItem() {
        return item;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
