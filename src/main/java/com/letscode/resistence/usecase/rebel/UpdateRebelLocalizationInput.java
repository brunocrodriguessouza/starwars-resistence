package com.letscode.resistence.usecase.rebel;

import com.letscode.resistence.domain.rebel.LocalizationTable;

public record UpdateRebelLocalizationInput(Long idRebel, LocalizationTable location) {
}
