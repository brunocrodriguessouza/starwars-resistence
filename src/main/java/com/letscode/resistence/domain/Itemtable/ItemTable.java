package com.letscode.resistence.domain.Itemtable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.letscode.resistence.domain.rebel.RebelTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity(name="item")
@NoArgsConstructor
@AllArgsConstructor
public class ItemTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "rebel_id")
    @JsonProperty("rebel")
    private RebelTable rebel;

    @Enumerated(EnumType.STRING)
    @Column(name="itemEnum")
    @JsonProperty("item")
    private ItemEnum itemEnum;
    private Integer quantity;

    public RebelTable getRebel() {
        return rebel;
    }

    public void setRebel(RebelTable rebel) {
        this.rebel = rebel;
    }

    public ItemEnum getItemEnum() {
        return itemEnum;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
