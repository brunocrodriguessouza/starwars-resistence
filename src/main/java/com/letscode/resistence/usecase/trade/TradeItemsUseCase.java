package com.letscode.resistence.usecase.trade;

import com.letscode.resistence.domain.Itemtable.ItemTable;
import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelTable;
import com.letscode.resistence.domain.trade.TradeItems;
import com.letscode.resistence.domain.trade.Trader;
import com.letscode.resistence.usecase.exception.TradeIsNotAllowedForTheSameIdException;
import com.letscode.resistence.usecase.exception.TradeItemsDoesNotExistsException;
import com.letscode.resistence.usecase.exception.TradeItemsWithInvalidQuantityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeItemsUseCase {

    private RebelRepository rebelRepository;

    public TradeItemsUseCase(RebelRepository rebelRepository){
        this.rebelRepository = rebelRepository;
    }


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

        applyTrade(trader1, rebel1, rebel2);
        rebelRepository.save(rebel1);
        rebelRepository.save(rebel2);
    }

    private void applyTrade(Trader trader, RebelTable rebel1, RebelTable rebel2) {
        for(ItemTable item: trader.getItems()){
            var itemTable = rebel1.getItems().stream()
                    .filter(it -> it.getItemEnum().equals(item.getItemEnum())).findFirst().orElseThrow(TradeItemsWithInvalidQuantityException::new);
            if(itemTable.getQuantity() >= item.getQuantity()){
                rebel2.addItem(item);
                rebel1.remove(item);
            }
        }
    }

    private boolean validateQuantity(TradeItems tradeItems) {
        var totalTrade1 = getTotalTrade(tradeItems.getTrader1());
        var totalTrade2 = getTotalTrade(tradeItems.getTrader2());
        return totalTrade1.equals(totalTrade2);
    }

    private Integer getTotalTrade(Trader trader) {
        return trader.getItems().stream()
                .map(itemTable -> itemTable.getItemEnum().getValue() * itemTable.getQuantity())
                .reduce(0, Integer::sum);
    }
}

