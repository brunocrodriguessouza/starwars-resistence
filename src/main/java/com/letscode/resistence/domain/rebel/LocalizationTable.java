package com.letscode.resistence.domain.rebel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity(name="localization")
@NoArgsConstructor
@AllArgsConstructor
public class LocalizationTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @OneToOne
    @JoinColumn(name = "rebel_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RebelTable rebel;

    private Long latitude;
    private Long longitude;

    @JsonProperty(value = "galaxy_name")
    private String galaxyName;
}
