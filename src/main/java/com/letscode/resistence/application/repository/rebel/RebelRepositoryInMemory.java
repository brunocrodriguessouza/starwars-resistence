package com.letscode.resistence.application.repository.rebel;

import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RebelRepositoryInMemory implements RebelRepository {

    List<RebelTable> database = new ArrayList<>();
    @Override
    public RebelTable save(RebelTable table) {
        long id = database.size() + 1;
        table.setId(id);
        database.add(table);
        return table;
    }

    @Override
    public Optional<RebelTable> findById(Long id) {
        return database.stream()
                .filter(rebelTable -> rebelTable.getId().equals(id)).findFirst();
    }


    @Override
    public RebelTable updateTraitorById(Long rebelId, boolean isTraitor) {
        int index = (int) (rebelId - 1);

        RebelTable rebel = database.stream()
                .filter(rebelTable -> rebelTable.getId().equals(rebelId)).findFirst().get();

        rebel.setTraitor(true);

        database.add(index, rebel);
        return rebel;
    }

}
