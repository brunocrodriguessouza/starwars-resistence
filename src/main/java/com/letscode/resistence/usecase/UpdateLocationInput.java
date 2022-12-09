package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.LocationTable;

public record UpdateLocationInput(Long idRebel, LocationTable location) {
}
