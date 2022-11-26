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
    public void updateLocationById(Long id, Long latitude, Long longitude) {
        int index = (int) (id - 1);
        var rebelTable = RebelTable.builder()
                .id(id)
                .latitude(latitude)
                .longitude(longitude)
                .build();
       database.add(index, rebelTable);
    }
}
