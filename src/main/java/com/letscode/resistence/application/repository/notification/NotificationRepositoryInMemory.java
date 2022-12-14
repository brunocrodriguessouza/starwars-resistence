package com.letscode.resistence.application.repository.notification;

import com.letscode.resistence.domain.notification.NotificationRepository;
import com.letscode.resistence.domain.notification.NotificationTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotificationRepositoryInMemory implements NotificationRepository {

    List<NotificationTable> database = new ArrayList<>();

    @Override
    public void save(NotificationTable table) {
        database.add(table);
    }

    @Override
    public Optional<NotificationTable> findByRebelIdAndNotifierIdExists(Long rebelId, Long notifierId) {
        return database.stream()
                .filter(notificationTable -> notificationTable.getRebelId().equals(rebelId)
                        && notificationTable.getNotifierId().equals(notifierId)).findFirst();
    }

    @Override
    public long countNotificationByRebelId(Long rebelId) {
        return database.stream()
                .filter(notificationTable -> notificationTable.getRebelId().equals(rebelId))
                .count();
    }
}
