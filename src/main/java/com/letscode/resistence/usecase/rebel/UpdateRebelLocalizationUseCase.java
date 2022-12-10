package com.letscode.resistence.usecase.rebel;

import com.letscode.resistence.domain.rebel.*;
import com.letscode.resistence.usecase.exception.RebelNotFoundException;
import com.letscode.resistence.usecase.rebel.UpdateRebelLocalizationInput;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateRebelLocalizationUseCase {

    private LocalizationRepository repository;

    public UpdateRebelLocalizationUseCase(LocalizationRepository repository) {
        this.repository = repository;
    }

    public void handle(UpdateRebelLocalizationInput input){

        Optional<LocalizationTable> table = repository.findByRebelId(input.idRebel());

        var rebelId = table.orElseThrow(RebelNotFoundException::new).getId();

        LocalizationTable localizationTable = table.get();

        localizationTable.setLatitude(input.location().getLatitude());
        localizationTable.setLongitude(input.location().getLongitude());
        localizationTable.setGalaxyName(input.location().getGalaxyName());

        repository.updateLocationByRebelId(rebelId, localizationTable);
    }

}

