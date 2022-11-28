package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.NotificationRepository;
import com.letscode.resistence.domain.rebel.NotificationTable;
import com.letscode.resistence.usecase.exception.RebelAlreadyNotifiedException;

import java.util.Optional;

public class NotifyRebelTraitorUseCase {

    private NotificationRepository repository;

    public NotifyRebelTraitorUseCase(NotificationRepository repository) {
        this.repository = repository;
    }

    public void handle(NotificationRebelTraitorInput input){
        Optional<NotificationTable> notification =  repository.findByRebelIdAndNotifierIdExists(input.rebelId(), input.notifierId());
        notification.ifPresent(s -> {
            throw new RebelAlreadyNotifiedException();
        });

        var table = mapToTable(input);
        repository.save(table);
    }

    private static NotificationTable mapToTable(NotificationRebelTraitorInput input){
        return NotificationTable.builder()
                .rebelId(input.rebelId())
                .notifierId(input.notifierId())
                .build();
    }
}

record NotificationRebelTraitorInput(Long rebelId, Long notifierId){}
