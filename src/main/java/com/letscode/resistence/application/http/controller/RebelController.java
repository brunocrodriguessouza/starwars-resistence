package com.letscode.resistence.application.http.controller;

import com.letscode.resistence.domain.rebel.RebelTable;
import com.letscode.resistence.usecase.AddRebelInput;
import com.letscode.resistence.usecase.AddRebelUseCase;
import com.letscode.resistence.usecase.UpdateRebelLocationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resistence")
public class RebelController{

    private final AddRebelUseCase addRebelUseCase;
    private final UpdateRebelLocationUseCase updateRebelLocationUseCase;

    @Autowired
    public RebelController(AddRebelUseCase addRebelUseCase, UpdateRebelLocationUseCase updateRebelLocationUseCase){
        this.addRebelUseCase = addRebelUseCase;
        this.updateRebelLocationUseCase = updateRebelLocationUseCase;
    }

    @PostMapping(value = "/rebel")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<RebelTable> addRebel(@RequestBody RebelTable rebelTable){
        var input = new AddRebelInput(
                rebelTable.getName(),
                rebelTable.getAge(),
                rebelTable.getGender(),
                rebelTable.getLocation().getLatitude(),
                rebelTable.getLocation().getLongitude(),
                rebelTable.getLocation().getGalaxyName()
        );

        addRebelUseCase.handle(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(rebelTable);
    }

}
