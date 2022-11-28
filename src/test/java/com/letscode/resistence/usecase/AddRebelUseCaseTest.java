package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.Item;
import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.domain.rebel.RebelRepositoryInMemory;
import com.letscode.resistence.domain.rebel.RebelTable;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AddRebelUseCaseTest {

    @Test
    public void shouldSaveRebel(){
        RebelRepository repository = new RebelRepositoryInMemory();
        AddRebelUseCase addRebelUseCase = new AddRebelUseCase(repository);
        Map<Item, Integer> inventory = Map.of(Item.WEAPON, 1, Item.MUNITION,1, Item.WATER,1,  Item.FOOD, 1);
        AddRebelInput input = new AddRebelInput("Gabriel", 12, "Male", false,125L, 123L, "M83", inventory);
        addRebelUseCase.handle(input);

        RebelTable rebel = repository.findById(1L).get();

        assertEquals("Gabriel", rebel.getName());
        assertEquals(12, rebel.getAge());
        assertEquals("Male", rebel.getGender());
        assertEquals(125L, rebel.getLatitude());
        assertEquals(123L, rebel.getLongitude());
        assertEquals("M83", rebel.getGalaxyName());
        assertEquals(false, rebel.isTraitor());
        assertEquals(inventory, rebel.getInventory());
        assertEquals(1, inventory.get(Item.WEAPON));
        assertEquals(1, inventory.get(Item.MUNITION));
        assertEquals(1, inventory.get(Item.WATER));
        assertEquals(1, inventory.get(Item.FOOD));
    }

}