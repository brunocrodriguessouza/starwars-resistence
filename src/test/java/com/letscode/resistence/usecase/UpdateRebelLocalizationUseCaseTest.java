package com.letscode.resistence.usecase;

import com.letscode.resistence.application.repository.localization.LocalizationRepositoryInMemory;
import com.letscode.resistence.domain.rebel.LocalizationRepository;
import com.letscode.resistence.domain.rebel.LocalizationTable;
import com.letscode.resistence.domain.rebel.RebelTable;
import com.letscode.resistence.usecase.exception.RebelNotFoundException;
import com.letscode.resistence.usecase.rebel.UpdateRebelLocalizationInput;
import com.letscode.resistence.usecase.rebel.UpdateRebelLocalizationUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UpdateRebelLocalizationUseCaseTest {

    @Test
    public void shouldThrowExceptionWhenLocalizationNotFound(){
       LocalizationRepository repository = new LocalizationRepositoryInMemory();
        UpdateRebelLocalizationUseCase useCase = new UpdateRebelLocalizationUseCase(repository);

        LocalizationTable location = LocalizationTable.builder()
                .latitude(132L)
                .longitude(133L)
                .galaxyName("M83")
                .build();

        UpdateRebelLocalizationInput input = new UpdateRebelLocalizationInput(1L, location);
        assertThrows(RebelNotFoundException.class, () -> useCase.handle(input));
    }

    @Test
    public void shouldUpdateLocalization(){
        LocalizationRepository repository = new LocalizationRepositoryInMemory();
        UpdateRebelLocalizationUseCase useCase = new UpdateRebelLocalizationUseCase(repository);

        LocalizationTable localization = LocalizationTable.builder()
                .rebel(RebelTable.builder().id(1L).build())
                .latitude(132L)
                .longitude(133L)
                .galaxyName("M83")
                .build();

        repository.save(localization);


        LocalizationTable locationToUpdate = LocalizationTable.builder()
                .rebel(RebelTable.builder().id(1L).build())
                .latitude(-1234L)
                .longitude(582L)
                .galaxyName("M85")
                .build();

        UpdateRebelLocalizationInput input = new UpdateRebelLocalizationInput(1L, locationToUpdate);
        useCase.handle(input);

        assertEquals(-1234L, localization.getLatitude());
        assertEquals(582L, localization.getLongitude());
        assertEquals("M85", localization.getGalaxyName());
    }

}