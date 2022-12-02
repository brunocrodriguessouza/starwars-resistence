package com.letscode.resistence.domain.rebel;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Trader {

    private Long rebelId;
    private List<ItemTable> items;

}
