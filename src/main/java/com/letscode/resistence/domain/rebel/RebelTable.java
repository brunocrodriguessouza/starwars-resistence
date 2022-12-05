package com.letscode.resistence.domain.rebel;

import com.letscode.resistence.domain.Itemtable.ItemTable;
import com.letscode.resistence.usecase.exception.ItemTableNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@Entity(name="rebel")
@NoArgsConstructor
@AllArgsConstructor
public class RebelTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private String gender;
    private boolean traitor;

    // TODO change to a new class
    private Long latitude;
    private Long longitude;
    private String galaxyName;

    @ElementCollection
//    @Column(name = "inventory")
//    @OneToOne(optional = false)
//    @JoinColumn(name = "id")
    private List<ItemTable> inventory;

    public void addItem(ItemTable item) {
        var itemTable = this.inventory.stream()
                .filter(it -> it.getItem().equals(item.getItem()))
                .findFirst()
                .orElseThrow(ItemTableNotFoundException::new);

        itemTable.setQuantity(itemTable.getQuantity() + item.getQuantity());
    }

    public void remove(ItemTable item) {
        var itemTable = this.inventory.stream()
                .filter(it -> it.getItem().equals(item.getItem()))
                .findFirst()
                .orElseThrow(ItemTableNotFoundException::new);

        itemTable.setQuantity(itemTable.getQuantity() - item.getQuantity());
    }
}

