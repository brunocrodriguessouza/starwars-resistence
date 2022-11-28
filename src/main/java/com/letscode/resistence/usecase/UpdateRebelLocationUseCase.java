package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelTable;
import com.letscode.resistence.usecase.exception.RebelNotFoundException;

import java.util.Optional;

public class UpdateRebelLocationUseCase {

    private RebelRepository repository;

    public UpdateRebelLocationUseCase(RebelRepository repository) {
        this.repository = repository;
    }

    public void handle(UpdateLocationInput input){
        Optional<RebelTable> table =  repository.findById(input.idRebel());
        Long id = table.orElseThrow(RebelNotFoundException::new).getId();
        repository.updateLocationById(id, input.latitude(), input.longitude(), input.galaxyName());
    }
}

record UpdateLocationInput(Long idRebel, Long latitude, Long longitude, String galaxyName){}
