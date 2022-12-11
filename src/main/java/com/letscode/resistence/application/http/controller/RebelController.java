package com.letscode.resistence.application.http.controller;

import com.letscode.resistence.domain.rebel.RebelTable;
import com.letscode.resistence.usecase.rebel.AddRebelInput;
import com.letscode.resistence.usecase.rebel.AddRebelUseCase;
import com.letscode.resistence.usecase.rebel.UpdateRebelLocalizationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RebelController{

    private final AddRebelUseCase addRebelUseCase;
    private final UpdateRebelLocalizationUseCase updateRebelLocalizationUseCase;

    @Autowired
    public RebelController(AddRebelUseCase addRebelUseCase, UpdateRebelLocalizationUseCase updateRebelLocalizationUseCase){
        this.addRebelUseCase = addRebelUseCase;
        this.updateRebelLocalizationUseCase = updateRebelLocalizationUseCase;
    }

    @PostMapping(value = "/rebel")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<RebelTable> addRebel(@RequestBody RebelTable rebelTable){
        var input = new AddRebelInput(
                rebelTable.getName(),
                rebelTable.getAge(),
                rebelTable.getGender(),
                rebelTable.getLocalization().getLatitude(),
                rebelTable.getLocalization().getLongitude(),
                rebelTable.getLocalization().getGalaxyName()
        );

        addRebelUseCase.handle(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(rebelTable);
    }

}
