package com.letscode.resistence.application;

import com.letscode.resistence.domain.rebel.LocalizationRepository;
import com.letscode.resistence.domain.rebel.LocalizationTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocalizationRepositoryInMemory implements LocalizationRepository {

    List<LocalizationTable> database = new ArrayList<>();

    @Override
    public LocalizationTable save(LocalizationTable table) {
        long id = database.size() + 1;
        table.setId(id);
        database.add(table);
        return table;
    }

    //    @Override
//    public RebelTable updateLocationById(Long id, LocalizationTable location) {
//        int index = (int) (id - 1);
//
//        RebelTable rebel = database.stream()
//                .filter(rebelTable -> rebelTable.getId().equals(id)).findFirst().get();
//
//        rebel.setLocation(location);
//
//       database.add(index, rebel);
//       return rebel;
//    }

    @Override
    public Optional<LocalizationTable> findByRebelId(Long rebelId) {
        return database.stream()
                .filter(localization -> localization.getRebel().getId().equals(rebelId)).findFirst();
    }

    @Override
    public void updateLocationByRebelId(Long rebelId, LocalizationTable location) {
        int index = (int) (rebelId - 1);

        LocalizationTable localizationTable = database.stream()
                .filter(loc -> loc.getRebel().getId().equals(rebelId)).findFirst().get();

        localizationTable.setLatitude(location.getLatitude());
        localizationTable.setLongitude(location.getLongitude());
        localizationTable.setGalaxyName(location.getGalaxyName());

       database.add(index, localizationTable);
    }
}
