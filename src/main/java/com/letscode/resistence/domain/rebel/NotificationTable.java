package com.letscode.resistence.domain.rebel;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NotificationTable {

    private Long rebelId;
    private Long notifierId;
}
