package com.letscode.resistence.usecase.rebel;

import com.letscode.resistence.domain.rebel.GenderEnum;

public record AddRebelInput(String name, Integer age, GenderEnum gender, Long latitude, Long longitude,
                            String galaxyName) {
}
