package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelRepositoryInMemory;
import com.letscode.resistence.domain.rebel.RebelTable;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AddRebelUseCaseTest {

    @Test
    public void shouldSaveRebel(){
        RebelRepository repository = new RebelRepositoryInMemory();
        AddRebelUseCase addRebelUseCase = new AddRebelUseCase(repository);
        AddRebelInput input = new AddRebelInput("Gabriel", 12, "Male", false,125L, 123L, "M83");
        addRebelUseCase.handle(input);

        RebelTable rebel = repository.findById(1L).get();

        assertEquals("Gabriel", rebel.getName());
        assertEquals(12, rebel.getAge());
        assertEquals("Male", rebel.getGender());
        assertEquals(125L, rebel.getLatitude());
        assertEquals(123L, rebel.getLongitude());
        assertEquals("M83", rebel.getGalaxyName());
        assertEquals(false, rebel.isTraitor());

    }

}