package com.letscode.resistence.domain.rebel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity(name="location")
@NoArgsConstructor
@AllArgsConstructor
public class LocationTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rebel_id")
    private RebelTable rebel;

    private Long latitude;
    private Long longitude;

    @JsonProperty(value = "galaxy_name")
    private String galaxyName;
}
