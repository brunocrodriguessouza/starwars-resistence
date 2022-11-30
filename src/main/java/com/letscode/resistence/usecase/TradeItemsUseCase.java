package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.InventoryTable;
import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelTable;
import com.letscode.resistence.usecase.exception.TradeItemsWithInvalidSizeException;

import java.util.List;
import java.util.Optional;

public class TradeItemsUseCase {

    private RebelRepository rebelRepository;

    public void handle(TradeItemsInput input){
        List<InventoryTable> inventoryTables = input.inventoryTable();
        if(inventoryTables.size() == 2){
            tradeItems(inventoryTables.get(0),inventoryTables.get(1));
        } else{
            throw new TradeItemsWithInvalidSizeException();
        }
    }

    private void tradeItems(InventoryTable offerer, InventoryTable receiver) {
        Optional<RebelTable> rebel1 = rebelRepository.findById(offerer.getRebelId());
        Optional<RebelTable> rebel2 = rebelRepository.findById(receiver.getRebelId());
    }
}

record TradeItemsInput(List<InventoryTable> inventoryTable){}

