package com.letscode.resistence.application.http.controller;

import com.letscode.resistence.domain.rebel.LocalizationTable;
import com.letscode.resistence.usecase.UpdateLocationInput;
import com.letscode.resistence.usecase.UpdateRebelLocationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resistence")
public class LocalizationController {

    private final UpdateRebelLocationUseCase updateRebelLocationUseCase;

    @Autowired
    public LocalizationController(UpdateRebelLocationUseCase updateRebelLocationUseCase){
        this.updateRebelLocationUseCase = updateRebelLocationUseCase;
    }

    @PutMapping("/rebel/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateRebelLocation(@RequestBody LocalizationTable location, @PathVariable("id") Long id ){

        var input = new UpdateLocationInput(id, location);
        updateRebelLocationUseCase.handle(input);

        ResponseEntity.status(HttpStatus.CREATED).body("Successfully changed");
    }
}
