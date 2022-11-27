package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelTable;

import java.util.Optional;

public class UpdateRebelTraitorUseCase {

    private RebelRepository repository;

    public UpdateRebelTraitorUseCase(RebelRepository repository) {
        this.repository = repository;
    }

    public void handle(UpdateRebelTraitorInput input){
        Optional<RebelTable> table =  repository.findById(input.id());
        Long id = table.orElseThrow(RebelNotFoundException::new).getId();
        repository.updateTraitorById(id);
    }
}

record UpdateRebelTraitorInput(Long id){}
