package com.letscode.resistence.application.http.controller;

import com.letscode.resistence.domain.trade.TradeItems;
import com.letscode.resistence.usecase.trade.TradeItemsInput;
import com.letscode.resistence.usecase.trade.TradeItemsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resistence")
public class TradeItemController {

    private final TradeItemsUseCase tradeItemsUseCase;

    public TradeItemController(TradeItemsUseCase tradeItemsUseCase) {
        this.tradeItemsUseCase = tradeItemsUseCase;
    }

    @PostMapping(value = "/rebel/trader")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Object> tradeItems(@RequestBody TradeItems tradeItems){
        var input = new TradeItemsInput(tradeItems);

        tradeItemsUseCase.handle(input);
        return ResponseEntity.status(HttpStatus.CREATED).body("Trader Success");
    }
}
