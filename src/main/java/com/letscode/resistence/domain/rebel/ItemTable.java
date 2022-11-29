package com.letscode.resistence.domain.rebel;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemTable {

    private Long id;
    private Item item;
    private Long quantity;
}
