package com.letscode.resistence.domain.rebel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.letscode.resistence.domain.Itemtable.ItemTable;
import com.letscode.resistence.usecase.exception.ItemTableNotFoundException;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Builder
@Getter
@Setter
@Entity(name="rebel")
@NoArgsConstructor
@AllArgsConstructor
public class RebelTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    private String name;
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private GenderEnum gender;
    private boolean traitor;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "rebel")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalizationTable localization;

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

