package com.letscode.resistence.application.repository.localization;

import com.letscode.resistence.domain.rebel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public class LocalizationRepositoryH2 implements LocalizationRepository {

    private final LocalizationRepositorySpringAdapter adapter;

    @Autowired
    public LocalizationRepositoryH2(LocalizationRepositorySpringAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    public LocalizationTable save(LocalizationTable table) {
        return adapter.save(table);
    }

    @Override
    public Optional<LocalizationTable> findByRebelId(Long id) {
        return adapter.findByRebelId(id);
    }

    @Override
    public void updateLocationByRebelId(Long rebelId, LocalizationTable localization) {
        adapter.updateLatitudeAndLongitudeAndGalaxyNameByRebelId(localization.getLatitude(), localization.getLongitude(), localization.getGalaxyName(), rebelId);
    }

}

@Repository
interface LocalizationRepositorySpringAdapter extends JpaRepository<LocalizationTable, Long>{
    Optional<LocalizationTable> findByRebelId(Long id);

    @Modifying
    @Query("update localization set latitude = :latitude, longitude = :longitude, galaxyName = :galaxy_name where rebel_id = :rebel_id")
    void updateLatitudeAndLongitudeAndGalaxyNameByRebelId(@Param("latitude") Long latitude, @Param("longitude") Long longitude, @Param("galaxy_name") String galaxyName, @Param("rebel_id") Long rebelId );
}
