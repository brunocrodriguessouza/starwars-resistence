package com.letscode.resistence.domain;

import com.letscode.resistence.domain.Itemtable.ItemTable;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Trader {

    private Long rebelId;
    private List<ItemTable> items;

}

