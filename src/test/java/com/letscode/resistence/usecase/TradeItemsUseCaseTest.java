package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.TradeItems;
import com.letscode.resistence.domain.rebel.Trader;
import com.letscode.resistence.domain.rebel.Item;
import com.letscode.resistence.domain.rebel.ItemTable;
import com.letscode.resistence.usecase.exception.TradeIsNotAllowedForTheSameIdException;
import com.letscode.resistence.usecase.exception.TradeItemsDoesNotExistsException;
import com.letscode.resistence.usecase.exception.TradeItemsWithInvalidQuantityException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TradeItemsUseCaseTest {

    @Test
    public void shouldThrowExceptionWhenTradeItemsDoesNotExists(){
        TradeItemsUseCase useCase = new TradeItemsUseCase();
        TradeItems tradeItems = new TradeItems(null, null);

        TradeItemsInput input = new TradeItemsInput(tradeItems);
        assertThrows(TradeItemsDoesNotExistsException.class, () -> useCase.handle(input));
    }
@Test
    public void shouldThrowExceptionWhenTheSameId(){
        TradeItemsUseCase useCase = new TradeItemsUseCase();

        ItemTable item = ItemTable.builder()
                .id(1L)
                .item(Item.FOOD)
                .quantity(1)
                .build();

        List<ItemTable> items = new ArrayList<>();
        items.add(item);

        Trader trader1 = Trader.builder()
                .rebelId(1L)
                .items(items)
                .build();

        Trader trader2 = Trader.builder()
                .rebelId(1L)
                .items(items)
                .build();

        TradeItems tradeItems = new TradeItems(trader1, trader2);

        TradeItemsInput input = new TradeItemsInput(tradeItems);
        assertThrows(TradeIsNotAllowedForTheSameIdException.class, () -> useCase.handle(input));
    }

}