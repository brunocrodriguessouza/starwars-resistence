package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.LocalizationTable;

public record UpdateLocationInput(Long idRebel, LocalizationTable location) {
}
