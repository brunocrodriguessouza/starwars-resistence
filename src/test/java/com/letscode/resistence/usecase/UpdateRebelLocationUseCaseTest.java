package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.LocationTable;
import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.application.RebelRepositoryInMemory;
import com.letscode.resistence.domain.rebel.RebelTable;
import com.letscode.resistence.usecase.exception.RebelNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateRebelLocationUseCaseTest {

    @Test
    public void shouldThrowExceptionWhenRebelNotFound(){
        RebelRepository repository = new RebelRepositoryInMemory();
        UpdateRebelLocationUseCase useCase = new UpdateRebelLocationUseCase(repository);

        LocationTable location = LocationTable.builder()
                .latitude(132L)
                .longitude(133L)
                .galaxyName("M83")
                .build();

        UpdateLocationInput input = new UpdateLocationInput(1L, location);
        assertThrows(RebelNotFoundException.class, () -> useCase.handle(input));
    }

    @Test
    public void shouldUpdateLocation(){
        RebelRepository repository = new RebelRepositoryInMemory();
        UpdateRebelLocationUseCase useCase = new UpdateRebelLocationUseCase(repository);

        LocationTable location = LocationTable.builder()
                .latitude(132L)
                .longitude(133L)
                .galaxyName("M83")
                .build();

        RebelTable rebel = RebelTable.builder()
                .id(1L)
                .name("Gabriel")
                .age(12)
                .gender("Male")
                .traitor(false)
                .location(location)
                .build();

        repository.save(rebel);
        LocationTable locationToUpdate = LocationTable.builder()
                .latitude(-1234L)
                .longitude(582L)
                .galaxyName("M85")
                .build();
        UpdateLocationInput input = new UpdateLocationInput(1L, locationToUpdate);
        useCase.handle(input);

        assertEquals("Gabriel", rebel.getName());
        assertEquals(12, rebel.getAge());
        assertEquals(-1234L, rebel.getLocation().getLatitude());
        assertEquals(582L, rebel.getLocation().getLongitude());
        assertEquals("M85", rebel.getLocation().getGalaxyName());
        assertEquals(false, rebel.isTraitor());
    }

}