package com.letscode.resistence.domain.rebel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.letscode.resistence.domain.Itemtable.ItemTable;
import com.letscode.resistence.usecase.exception.ItemTableNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Builder
@Data
@Entity(name="rebel")
@NoArgsConstructor
@AllArgsConstructor
public class RebelTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    private String name;
    private Integer age;
    private String gender;
    private boolean traitor;

    // TODO change to a new class
    private Long latitude;
    private Long longitude;

    @JsonProperty(value = "galaxy_name")
    private String galaxyName;

    @OneToMany(fetch = LAZY, mappedBy = "rebel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ItemTable> items;

    public void addItem(ItemTable item) {
        var itemTable = this.items.stream()
                .filter(it -> it.getItemEnum().equals(item.getItemEnum()))
                .findFirst()
                .orElseThrow(ItemTableNotFoundException::new);

        itemTable.setQuantity(itemTable.getQuantity() + item.getQuantity());
    }

    public void remove(ItemTable item) {
        var itemTable = this.items.stream()
                .filter(it -> it.getItemEnum().equals(item.getItemEnum()))
                .findFirst()
                .orElseThrow(ItemTableNotFoundException::new);

        itemTable.setQuantity(itemTable.getQuantity() - item.getQuantity());
    }
}

