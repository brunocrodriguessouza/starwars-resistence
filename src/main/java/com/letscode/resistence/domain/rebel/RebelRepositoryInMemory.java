package com.letscode.resistence.domain.rebel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RebelRepositoryInMemory implements RebelRepository{

    List<RebelTable> database = new ArrayList<>();
    @Override
    public void save(RebelTable table) {
        long id = database.size() + 1;
        table.setId(id);
        database.add(table);
    }

    @Override
    public Optional<RebelTable> findById(Long id) {
        return database.stream()
                .filter(rebelTable -> rebelTable.getId().equals(id)).findFirst();
    }

    @Override
    public void updateLocationById(Long id, Long latitude, Long longitude, String galaxyName) {
        int index = (int) (id - 1);

        RebelTable rebel = database.stream()
                .filter(rebelTable -> rebelTable.getId().equals(id)).findFirst().get();

        rebel.setLatitude(latitude);
        rebel.setLongitude(longitude);
        rebel.setGalaxyName(galaxyName);

       database.add(index, rebel);
    }

    @Override
    public void updateTraitorById(Long rebelId, boolean isTraitor) {
        int index = (int) (rebelId - 1);

        RebelTable rebel = database.stream()
                .filter(rebelTable -> rebelTable.getId().equals(rebelId)).findFirst().get();

        rebel.setTraitor(true);

        database.add(index, rebel);
    }

}
