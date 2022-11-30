package com.letscode.resistence.domain.rebel;

import lombok.Builder;

@Builder
public class ItemTable {

    private Long id;
    private Item item;
    private Long quantity;

    public Item getItem() {
        return item;
    }

    public Long getQuantity() {
        return quantity;
    }
}
