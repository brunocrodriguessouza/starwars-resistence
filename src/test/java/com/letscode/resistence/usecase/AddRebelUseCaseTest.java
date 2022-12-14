package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.GenderEnum;
import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.application.repository.rebel.RebelRepositoryInMemory;
import com.letscode.resistence.domain.rebel.RebelTable;
import com.letscode.resistence.usecase.rebel.AddRebelInput;
import com.letscode.resistence.usecase.rebel.AddRebelUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddRebelUseCaseTest {

    @Test
    public void shouldSaveRebel(){
        RebelRepository repository = new RebelRepositoryInMemory();
        AddRebelUseCase addRebelUseCase = new AddRebelUseCase(repository);
        AddRebelInput input = new AddRebelInput("Gabriel", 12, GenderEnum.MALE, 125L, 123L, "M83");
        addRebelUseCase.handle(input);

        RebelTable rebel = repository.findById(1L).get();

        assertEquals(1L, rebel.getId());
        assertEquals("Gabriel", rebel.getName());
        assertEquals(12, rebel.getAge());
        assertEquals(GenderEnum.MALE, rebel.getGender());
        assertEquals(125L, rebel.getLocalization().getLatitude());
        assertEquals(123L, rebel.getLocalization().getLongitude());
        assertEquals("M83", rebel.getLocalization().getGalaxyName());
        assertEquals(false, rebel.isTraitor());

        assertEquals(4, rebel.getItems().get(0).getItemEnum().getValue());
        assertEquals(3, rebel.getItems().get(1).getItemEnum().getValue());
        assertEquals(2, rebel.getItems().get(2).getItemEnum().getValue());
        assertEquals(1, rebel.getItems().get(3).getItemEnum().getValue());

        assertEquals(1, rebel.getItems().get(0).getQuantity());
        assertEquals(1, rebel.getItems().get(1).getQuantity());
        assertEquals(1, rebel.getItems().get(2).getQuantity());
        assertEquals(1, rebel.getItems().get(3).getQuantity());
    }

}