package com.letscode.resistence.application.http.controller;

import com.letscode.resistence.domain.rebel.LocalizationTable;
import com.letscode.resistence.usecase.UpdateLocalizationInput;
import com.letscode.resistence.usecase.UpdateRebelLocalizationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resistence")
public class LocalizationController {

    private final UpdateRebelLocalizationUseCase updateRebelLocalizationUseCase;

    @Autowired
    public LocalizationController(UpdateRebelLocalizationUseCase updateRebelLocalizationUseCase){
        this.updateRebelLocalizationUseCase = updateRebelLocalizationUseCase;
    }

    @PutMapping("/rebel/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateRebelLocalization(@RequestBody LocalizationTable location, @PathVariable("id") Long id ){

        var input = new UpdateLocalizationInput(id, location);
        updateRebelLocalizationUseCase.handle(input);

        ResponseEntity.status(HttpStatus.CREATED).body("Successfully changed");
    }
}
