package com.letscode.resistence.domain.notification;

import java.util.Optional;

public interface NotificationRepository {

    void save(NotificationTable table);

    Optional<NotificationTable> findByRebelIdAndNotifierIdExists(Long rebelId, Long notifierId);

    long countNotificationByRebelId(Long rebelId);
}
