package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.NotificationRepository;
import com.letscode.resistence.domain.rebel.NotificationTable;
import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.usecase.exception.RebelAlreadyNotifiedException;

import java.util.Optional;

public class NotifyRebelTraitorUseCase {

    private NotificationRepository repository;
    private RebelRepository rebelRepository;

    public NotifyRebelTraitorUseCase(NotificationRepository repository, RebelRepository rebelRepository) {
        this.repository = repository;
        this.rebelRepository = rebelRepository;
    }

    public void handle(NotificationRebelTraitorInput input){
        Optional<NotificationTable> notification =  repository.findByRebelIdAndNotifierIdExists(input.rebelId(), input.notifierId());
        notification.ifPresent(n -> {
            throw new RebelAlreadyNotifiedException();
        });

        var table = mapToTable(input);
        repository.save(table);

        long count = repository.countNotificationByRebelId(input.rebelId());
        if (count >= 3) {
            rebelRepository.updateTraitorById(input.rebelId(), true);
        }
    }

    private static NotificationTable mapToTable(NotificationRebelTraitorInput input){
        return NotificationTable.builder()
                .rebelId(input.rebelId())
                .notifierId(input.notifierId())
                .build();
    }
}

record NotificationRebelTraitorInput(Long rebelId, Long notifierId){}
