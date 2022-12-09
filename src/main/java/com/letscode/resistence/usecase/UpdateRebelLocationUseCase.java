package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelTable;
import com.letscode.resistence.usecase.exception.RebelNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateRebelLocationUseCase {

    private RebelRepository repository;

    public UpdateRebelLocationUseCase(RebelRepository repository) {
        this.repository = repository;
    }

    public RebelTable handle(UpdateLocationInput input){
        Optional<RebelTable> table = repository.findById(input.idRebel());
        Long id = table.orElseThrow(RebelNotFoundException::new).getId();
        return repository.updateLocationById(id, input.location());
    }
}

