package com.letscode.resistence.domain.rebel;

import java.util.Optional;

public interface LocalizationRepository {

    LocalizationTable save(LocalizationTable table);

    Optional<LocalizationTable> findByRebelId(Long id);

    void updateLocationByRebelId(Long rebelId, LocalizationTable location);
}
