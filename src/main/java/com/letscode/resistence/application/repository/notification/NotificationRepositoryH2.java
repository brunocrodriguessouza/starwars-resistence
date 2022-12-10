package com.letscode.resistence.application.repository.notification;

import com.letscode.resistence.domain.notification.NotificationRepository;
import com.letscode.resistence.domain.notification.NotificationTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public class NotificationRepositoryH2 implements NotificationRepository {

    private final NotificationRepositorySpringAdapter adapter;

    @Autowired
    public NotificationRepositoryH2(NotificationRepositorySpringAdapter adapter){
        this.adapter = adapter;
    }


    @Override
    public void save(NotificationTable table) {
        adapter.save(table);
    }

    @Override
    public Optional<NotificationTable> findByRebelIdAndNotifierIdExists(Long rebelId, Long notifierId) {
        return adapter.findByRebelIdAndNotifierId(rebelId, notifierId);
    }

    @Override
    public long countNotificationByRebelId(Long rebelId) {
        return adapter.countNotificationByRebelId(rebelId);
    }
}

@Repository
interface NotificationRepositorySpringAdapter extends JpaRepository<NotificationTable, Long>{
    Optional<NotificationTable> findById(Long id);

    Optional<NotificationTable> findByRebelIdAndNotifierId(Long rebelId, Long notifierId);

    long countNotificationByRebelId(Long rebelId);
}
