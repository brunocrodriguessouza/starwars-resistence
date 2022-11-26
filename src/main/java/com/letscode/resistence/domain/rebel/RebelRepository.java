package com.letscode.resistence.domain.rebel;

import java.util.Optional;

public interface RebelRepository {

    void save(RebelTable table);

    Optional<RebelTable> findById(Long id);
}
