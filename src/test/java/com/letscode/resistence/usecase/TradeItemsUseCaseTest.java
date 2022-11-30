package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.InventoryTable;
import com.letscode.resistence.domain.rebel.Item;
import com.letscode.resistence.domain.rebel.ItemTable;
import com.letscode.resistence.usecase.exception.RebelAlreadyNotifiedException;
import com.letscode.resistence.usecase.exception.TradeItemsWithInvalidSizeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


class TradeItemsUseCaseTest {

    @Test
    public void shouldThrowExceptionWhenInventoryIsDifferentTwo(){
        TradeItemsUseCase useCase = new TradeItemsUseCase();

        ItemTable item = ItemTable.builder()
                .id(1L)
                .item(Item.FOOD)
                .quantity(1L)
                .build();

        List<ItemTable> items = new ArrayList<>();
        items.add(item);

        InventoryTable inventoryTable = InventoryTable.builder()
                .rebelId(1L)
                .items(items)
                .build();

        List<InventoryTable> inventoryTables = new ArrayList<>();
        inventoryTables.add(inventoryTable);

        TradeItemsInput input = new TradeItemsInput(inventoryTables);
        assertThrows(TradeItemsWithInvalidSizeException.class, () -> useCase.handle(input));
    }

}