package com.letscode.resistence.domain.rebel;

import java.util.Optional;
import java.util.stream.Stream;

public interface NotificationRepository {

    void save(NotificationTable table);

    Optional<NotificationTable> findByRebelIdAndNotifierIdExists(Long rebelId, Long notifierId);

    long countNotificationByRebelId(Long rebelId);
}
