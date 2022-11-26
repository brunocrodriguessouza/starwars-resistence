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
    public void patchLocation(RebelTable table) {
        int index = (int) (table.getId() - 1);
        var rebelTable = RebelTable.builder()
                .id(table.getId())
                .latitude(table.getLatitude())
                .longitude(table.getLongitude())
                .build();
       database.add(index, rebelTable);
    }
}
