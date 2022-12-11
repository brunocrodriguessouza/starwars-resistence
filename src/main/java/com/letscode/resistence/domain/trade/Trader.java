package com.letscode.resistence.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.letscode.resistence.domain.Itemtable.ItemTable;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Trader {

    @JsonProperty("rebel_id")
    private Long rebelId;
    private List<ItemTable> items;
}

