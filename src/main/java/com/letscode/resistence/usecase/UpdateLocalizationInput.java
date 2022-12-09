package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.LocalizationTable;

public record UpdateLocalizationInput(Long idRebel, LocalizationTable location) {
}
