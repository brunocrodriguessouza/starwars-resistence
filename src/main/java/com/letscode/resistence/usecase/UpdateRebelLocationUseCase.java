package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.*;
import com.letscode.resistence.usecase.exception.RebelNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateRebelLocationUseCase {

    private LocalizationRepository repository;

    public UpdateRebelLocationUseCase(LocalizationRepository repository) {
        this.repository = repository;
    }

    public void handle(UpdateLocationInput input){

        Optional<LocalizationTable> table = repository.findByRebelId(input.idRebel());

        var rebelId = table.orElseThrow(RebelNotFoundException::new).getId();

        LocalizationTable localizationTable = table.get();

        localizationTable.setLatitude(input.location().getLatitude());
        localizationTable.setLongitude(input.location().getLongitude());
        localizationTable.setGalaxyName(input.location().getGalaxyName());

        repository.updateLocationByRebelId(rebelId, localizationTable);
    }

}

