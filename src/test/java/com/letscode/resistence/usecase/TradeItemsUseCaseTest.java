package com.letscode.resistence.usecase;

import com.letscode.resistence.application.repository.rebel.RebelRepositoryInMemory;
import com.letscode.resistence.domain.Itemtable.ItemEnum;
import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.trade.TradeItems;
import com.letscode.resistence.domain.trade.Trader;
import com.letscode.resistence.domain.Itemtable.ItemTable;
import com.letscode.resistence.usecase.exception.TradeIsNotAllowedForTheSameIdException;
import com.letscode.resistence.usecase.exception.TradeItemsDoesNotExistsException;
import com.letscode.resistence.usecase.exception.TradeItemsWithInvalidQuantityException;
import com.letscode.resistence.usecase.trade.TradeItemsInput;
import com.letscode.resistence.usecase.trade.TradeItemsUseCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TradeItemsUseCaseTest {

    @Test
    public void shouldThrowExceptionWhenTradeItemsDoesNotExists(){
        RebelRepository repository = new RebelRepositoryInMemory();
        TradeItemsUseCase useCase = new TradeItemsUseCase(repository);
        TradeItems tradeItems = new TradeItems(null, null);

        TradeItemsInput input = new TradeItemsInput(tradeItems);
        assertThrows(TradeItemsDoesNotExistsException.class, () -> useCase.handle(input));
    }
    @Test
    public void shouldThrowExceptionWhenTheSameId(){
        RebelRepository repository = new RebelRepositoryInMemory();
        TradeItemsUseCase useCase = new TradeItemsUseCase(repository);

        ItemTable item = ItemTable.builder()
                .id(1L)
                .itemEnum(ItemEnum.FOOD)
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
    @Test
    public void shouldThrowExceptionWhenTradeItemsWithInvalidQuantity(){
        RebelRepository repository = new RebelRepositoryInMemory();
        TradeItemsUseCase useCase = new TradeItemsUseCase(repository);

        ItemTable item1 = ItemTable.builder()
                .id(1L)
                .itemEnum(ItemEnum.FOOD)
                .quantity(1)
                .build();

        List<ItemTable> items1 = new ArrayList<>();
        items1.add(item1);

        List<ItemTable> items2 = new ArrayList<>();
        items1.add(item1);

        Trader trader1 = Trader.builder()
                .rebelId(1L)
                .items(items1)
                .build();

        Trader trader2 = Trader.builder()
                .rebelId(2L)
                .items(items2)
                .build();

        TradeItems tradeItems = new TradeItems(trader1, trader2);

        TradeItemsInput input = new TradeItemsInput(tradeItems);
        assertThrows(TradeItemsWithInvalidQuantityException.class, () -> useCase.handle(input));
    }

    @Test
    public void shouldThrowExceptionWhenTradeItemsWithInvalidQuantity2(){
        RebelRepository repository = new RebelRepositoryInMemory();
        TradeItemsUseCase useCase = new TradeItemsUseCase(repository);

        ItemTable item1 = ItemTable.builder()
                .id(1L)
                .itemEnum(ItemEnum.FOOD)
                .quantity(1)
                .build();

        List<ItemTable> items1 = new ArrayList<>();
        items1.add(item1);

        List<ItemTable> items2 = new ArrayList<>();
        items1.add(item1);

        Trader trader1 = Trader.builder()
                .rebelId(1L)
                .items(items1)
                .build();

        Trader trader2 = Trader.builder()
                .rebelId(3L)
                .items(items2)
                .build();

        TradeItems tradeItems = new TradeItems(trader1, trader2);

        TradeItemsInput input = new TradeItemsInput(tradeItems);
        assertThrows(TradeItemsWithInvalidQuantityException.class, () -> useCase.handle(input));
    }

}