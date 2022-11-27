package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelRepositoryInMemory;
import com.letscode.resistence.domain.rebel.RebelTable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateRebelTraitorUseCaseTest {

    @Test
    public void shouldThrowExceptionWhenRebelNotFound(){
        RebelRepository repository = new RebelRepositoryInMemory();
        UpdateRebelTraitorUseCase useCase = new UpdateRebelTraitorUseCase(repository);
        UpdateRebelTraitorInput input = new UpdateRebelTraitorInput(1L);
        assertThrows(RebelNotFoundException.class, () -> useCase.handle(input));
    }

    @Test
    public void shouldReportAsTraitor(){
        RebelRepository repository = new RebelRepositoryInMemory();
        UpdateRebelTraitorUseCase useCase = new UpdateRebelTraitorUseCase(repository);

        RebelTable rebel = RebelTable.builder()
                .id(1L)
                .name("Gabriel")
                .age(12)
                .gender("Male")
                .latitude(125L)
                .longitude(123L)
                .galaxyName("M83")
                .build();

        repository.save(rebel);
        UpdateRebelTraitorInput input = new UpdateRebelTraitorInput(1L);
        useCase.handle(input);

        assertEquals("Gabriel", rebel.getName());
        assertEquals(12, rebel.getAge());
        assertEquals(125L, rebel.getLatitude());
        assertEquals(123L, rebel.getLongitude());
        assertEquals("M83", rebel.getGalaxyName());
        assertEquals(true, rebel.isTraitor());
    }

}