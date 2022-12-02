package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.*;
import com.letscode.resistence.usecase.exception.TradeIsNotAllowedForTheSameIdException;
import com.letscode.resistence.usecase.exception.TradeItemsDoesNotExistsException;
import com.letscode.resistence.usecase.exception.TradeItemsWithInvalidQuantityException;

public class TradeItemsUseCase {

    private RebelRepository rebelRepository;

    public void handle(TradeItemsInput input){

        validateTrade(input.tradeItems());
        tradeItems(input.tradeItems());
    }

    private void validateTrade(TradeItems tradeItems) {
        if(tradeItems.getTrader1() == null || tradeItems.getTrader2() == null){
            throw new TradeItemsDoesNotExistsException();
        }

        if(tradeItems.getTrader1().getRebelId().equals(tradeItems.getTrader2().getRebelId())){
            throw new TradeIsNotAllowedForTheSameIdException();
        }

        if(!validateQuantity(tradeItems)){
            throw new TradeItemsWithInvalidQuantityException();
        }
    }

    private void tradeItems(TradeItems tradeItems) {
        var trader1 = tradeItems.getTrader1();
        var trader2 = tradeItems.getTrader2();

        RebelTable rebel1 = rebelRepository.findById(trader1.getRebelId()).orElseThrow(TradeItemsWithInvalidQuantityException::new);
        RebelTable rebel2 = rebelRepository.findById(trader2.getRebelId()).orElseThrow(TradeItemsWithInvalidQuantityException::new);

        Trader transaction1 = Trader.builder().rebelId(trader1.getRebelId()).build();
        Trader transaction2 = Trader.builder().rebelId(trader2.getRebelId()).build();

        try{
            applyTrade(trader1, rebel1, rebel2, transaction1);
            applyTrade(trader2, rebel2, rebel1, transaction2);
            rebelRepository.save(rebel1);
            rebelRepository.save(rebel2);

        }catch(Exception e){
            rollbackTrade(rebel1, rebel2, transaction1);
            rollbackTrade(rebel2, rebel1, transaction2);
        }

    }

    private void applyTrade(Trader trader, RebelTable rebel1, RebelTable rebel2, Trader transaction) {
        for(ItemTable item: trader.getItems()){
            var itemTable = rebel1.getInventory().stream()
                    .filter(it -> it.getItem().equals(item.getItem())).findFirst().orElseThrow(TradeItemsWithInvalidQuantityException::new);
            if(itemTable.getQuantity() >= item.getQuantity()){
                rebel2.addItem(item);
                rebel1.remove(item);
            }
            transaction.getItems().add(item);
        }
    }

    private void rollbackTrade(RebelTable rebel1, RebelTable rebel2, Trader transaction1) {
        for(ItemTable item: transaction1.getItems()){
            rebel1.addItem(item);
            rebel2.remove(item);
        }
    }

    private boolean validateQuantity(TradeItems tradeItems) {
        var totalTrade1 = getTotalTrade(tradeItems.getTrader1());
        var totalTrade2 = getTotalTrade(tradeItems.getTrader2());
        return totalTrade1.equals(totalTrade2);
    }

    private Integer getTotalTrade(Trader trader) {
        return trader.getItems().stream()
                .map(itemTable -> itemTable.getItem().getValue() * itemTable.getQuantity())
                .reduce(0, Integer::sum);
    }
}

record TradeItemsInput(TradeItems tradeItems){}

