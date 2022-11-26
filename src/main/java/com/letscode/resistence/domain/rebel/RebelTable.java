package com.letscode.resistence.domain.rebel;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RebelTable {

    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private Long latitude;
    private Long longitude;
    private String galaxyName;
}

