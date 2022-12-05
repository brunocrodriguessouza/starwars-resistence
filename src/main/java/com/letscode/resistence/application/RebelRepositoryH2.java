package com.letscode.resistence.application;

import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelTable;
import com.letscode.resistence.usecase.exception.RebelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public class RebelRepositoryH2 implements RebelRepository {

    private final RebelRepositorySpringAdapter adapter;

    @Autowired
    public RebelRepositoryH2(RebelRepositorySpringAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    public RebelTable save(RebelTable rebel) {
        return adapter.save(rebel);
    }

    @Override
    public Optional<RebelTable> findById(Long id) {
        return adapter.findById(id);
    }

    @Override
    public RebelTable updateLocationById(Long id, Long latitude, Long longitude, String galaxyName) {
        var rebel = findById(id).orElseThrow((RebelNotFoundException::new));
        rebel.setLatitude(latitude);
        rebel.setLongitude(longitude);
        rebel.setGalaxyName(galaxyName);
        return adapter.save(rebel);
    }

    @Override
    public RebelTable updateTraitorById(Long rebelId, boolean isTraitor) {
        var rebel = findById(rebelId).orElseThrow((RebelNotFoundException::new));
        rebel.setTraitor(isTraitor);
        return adapter.save(rebel);
    }
}

@Repository
interface RebelRepositorySpringAdapter extends JpaRepository<RebelTable, Long>{
    Optional<RebelTable> findById(Long id);
}
