package com.letscode.resistence.domain.rebel;

import java.util.Optional;

public interface RebelRepository {

    RebelTable save(RebelTable table);

    Optional<RebelTable> findById(Long id);

    RebelTable updateLocationById(Long id, Long latitude, Long longitude, String name);

    RebelTable updateTraitorById(Long rebelId, boolean isTraitor);
}
