package com.letscode.resistence.application.http.controller;

import com.letscode.resistence.domain.rebel.RebelTable;
import com.letscode.resistence.usecase.AddRebelInput;
import com.letscode.resistence.usecase.AddRebelUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resistence")
public class RebelController{

    private final AddRebelUseCase addRebelUseCase;

    @Autowired
    RebelController(AddRebelUseCase addRebelUseCase){
        this.addRebelUseCase = addRebelUseCase;
    }

    @PostMapping(value = "/rebel")
    public ResponseEntity<RebelTable> addRebel(@RequestBody RebelTable rebelTable){
        var input = new AddRebelInput(
                rebelTable.getName(),
                rebelTable.getAge(),
                rebelTable.getGender(),
                rebelTable.getLatitude(),
                rebelTable.getLongitude(),
                rebelTable.getGalaxyName()
        );

        addRebelUseCase.handle(input);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(rebelTable);
    }
}
