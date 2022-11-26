package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelTable;

import java.util.Optional;

public class UpdateRebelLocationUseCase {

    private RebelRepository repository;

    public UpdateRebelLocationUseCase(RebelRepository repository) {
        this.repository = repository;
    }

    public void handle(UpdateLocationInput input){
        Optional<RebelTable> table =  repository.findById(input.idRebel());
        RebelTable rebelTable = table.orElseThrow(RebelNotFoundException::new);
        repository.patchLocation(rebelTable);

    }
}

record UpdateLocationInput(Long idRebel, Long latitude, Long longitude){}
