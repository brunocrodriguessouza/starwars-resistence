package com.letscode.resistence.usecase.notification;

import com.letscode.resistence.domain.notification.NotificationRepository;
import com.letscode.resistence.domain.notification.NotificationTable;
import com.letscode.resistence.domain.rebel.RebelRepository;
import com.letscode.resistence.usecase.exception.RebelAlreadyNotifiedException;
import com.letscode.resistence.usecase.notification.NotifyRebelTraitorInput;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotifyRebelTraitorUseCase {

    private NotificationRepository repository;
    private RebelRepository rebelRepository;

    public NotifyRebelTraitorUseCase(NotificationRepository repository, RebelRepository rebelRepository) {
        this.repository = repository;
        this.rebelRepository = rebelRepository;
    }

    public void handle(NotifyRebelTraitorInput input){
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

    private static NotificationTable mapToTable(NotifyRebelTraitorInput input){
        return NotificationTable.builder()
                .rebelId(input.rebelId())
                .notifierId(input.notifierId())
                .build();
    }
}
