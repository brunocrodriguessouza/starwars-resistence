package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelRepositoryInMemory;
import com.letscode.resistence.domain.rebel.RebelTable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateRebelLocationUseCaseTest {

    @Test
    public void shouldThrowExceptionWhenRebelNotFound(){
        RebelRepository repository = new RebelRepositoryInMemory();
        UpdateRebelLocationUseCase useCase = new UpdateRebelLocationUseCase(repository);
        UpdateLocationInput input = new UpdateLocationInput(1L, null, null);

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
                .longitude(123L)
                .latitude(125L)
                .galaxyName("Vialactea")
                .build();

        repository.save(rebel);
        UpdateLocationInput input = new UpdateLocationInput(1L, -1234L, 582L);
        useCase.handle(input);
    }

}