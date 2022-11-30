package com.letscode.resistence.usecase;

public class NegotiateItems {

    public void handle(NegotiateItemsInput input){
//        var table = mapToTable(input);
//        repository.save(table);
    }
}

record NegotiateItemsInput(String item1, String item2){}

