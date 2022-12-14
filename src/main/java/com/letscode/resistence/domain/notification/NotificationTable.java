package com.letscode.resistence.domain.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Getter
@Entity(name="notification")
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("rebel_id")
    private Long rebelId;

    @JsonProperty("notifier_id")
    private Long notifierId;
}
