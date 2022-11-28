package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelRepositoryInMemory;
import com.letscode.resistence.domain.rebel.RebelTable;
import com.letscode.resistence.usecase.exception.RebelNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateRebelLocationUseCaseTest {

    @Test
    public void shouldThrowExceptionWhenRebelNotFound(){
        RebelRepository repository = new RebelRepositoryInMemory();
        UpdateRebelLocationUseCase useCase = new UpdateRebelLocationUseCase(repository);
        UpdateLocationInput input = new UpdateLocationInput(1L, 132L, 165L, "M83");
        assertThrows(RebelNotFoundException.class, () -> useCase.handle(input));
    }

    @Test
    public void shouldUpdateLocation(){
        RebelRepository repository = new RebelRepositoryInMemory();
        UpdateRebelLocationUseCase useCase = new UpdateRebelLocationUseCase(repository);

        RebelTable rebel = RebelTable.builder()
                .id(1L)
                .name("Gabriel")
                .age(12)
                .gender("Male")
                .traitor(false)
                .longitude(123L)
                .latitude(125L)
                .galaxyName("M83")
                .build();

        repository.save(rebel);
        UpdateLocationInput input = new UpdateLocationInput(1L, -1234L, 582L, "M85");
        useCase.handle(input);

        assertEquals("Gabriel", rebel.getName());
        assertEquals(12, rebel.getAge());
        assertEquals(-1234L, rebel.getLatitude());
        assertEquals(582L, rebel.getLongitude());
        assertEquals("M85", rebel.getGalaxyName());
        assertEquals(false, rebel.isTraitor());
    }

}