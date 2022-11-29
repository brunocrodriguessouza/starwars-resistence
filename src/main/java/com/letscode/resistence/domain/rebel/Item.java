package com.letscode.resistence.domain.rebel;

public enum Item {

    WEAPON("Weapon", 4),
    MUNITION("Munition", 3),
    WATER("Water", 2),
    FOOD("Food", 1);

    private String itemName;
    private Integer value;

    Item(final String itemName, final Integer value ){
        this.itemName = itemName;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
