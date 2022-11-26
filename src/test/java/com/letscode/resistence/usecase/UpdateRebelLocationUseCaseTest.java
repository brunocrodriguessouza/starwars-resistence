package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelRepositoryInMemory;
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

}