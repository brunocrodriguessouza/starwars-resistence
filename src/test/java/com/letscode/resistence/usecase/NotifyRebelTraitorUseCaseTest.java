package com.letscode.resistence.usecase;

import com.letscode.resistence.application.RebelRepositoryInMemory;
import com.letscode.resistence.domain.notification.NotificationRepository;
import com.letscode.resistence.application.NotificationRepositoryInMemory;
import com.letscode.resistence.domain.notification.NotificationTable;
import com.letscode.resistence.domain.rebel.*;
import com.letscode.resistence.usecase.exception.RebelAlreadyNotifiedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotifyRebelTraitorUseCaseTest {

    @Test
    public void shouldThrowExceptionWhenRebelAlreadyNotified(){
        NotificationRepository repository = new NotificationRepositoryInMemory();
        RebelRepository rebelRepository = new RebelRepositoryInMemory();
        NotifyRebelTraitorUseCase useCase = new NotifyRebelTraitorUseCase(repository, rebelRepository);

        NotificationTable notification = NotificationTable.builder()
                .rebelId(1L)
                .notifierId(2L)
                .build();

        repository.save(notification);

        NotificationRebelTraitorInput input = new NotificationRebelTraitorInput(1L, 2L);
        assertThrows(RebelAlreadyNotifiedException.class, () -> useCase.handle(input));
    }

    @Test
    public void shouldNotifyAsTraitorSuccessfully(){
        NotificationRepository repository = new NotificationRepositoryInMemory();
        RebelRepository rebelRepository = new RebelRepositoryInMemory();
        NotifyRebelTraitorUseCase useCase = new NotifyRebelTraitorUseCase(repository, rebelRepository);

        NotificationRebelTraitorInput input = new NotificationRebelTraitorInput(1L, 2L);
        useCase.handle(input);

        NotificationTable notification = repository.findByRebelIdAndNotifierIdExists(1L, 2l).get();

        assertEquals(1L, notification.getRebelId());
        assertEquals(2L, notification.getNotifierId());

    }

    @Test
    public void shouldUpdateTraitorById(){
        NotificationRepository repository = new NotificationRepositoryInMemory();
        RebelRepository rebelRepository = new RebelRepositoryInMemory();

        rebelRepository.save(RebelTable.builder().id(1l).build());

        NotifyRebelTraitorUseCase useCase = new NotifyRebelTraitorUseCase(repository, rebelRepository);

        NotificationRebelTraitorInput input = new NotificationRebelTraitorInput(1L, 2L);
        useCase.handle(input);

        NotificationRebelTraitorInput input2 = new NotificationRebelTraitorInput(1L, 3L);
        useCase.handle(input2);

        NotificationRebelTraitorInput input3 = new NotificationRebelTraitorInput(1L, 4L);
        useCase.handle(input3);

        NotificationTable notification = repository.findByRebelIdAndNotifierIdExists(1L, 2l).get();
        Long count = repository.countNotificationByRebelId(1L);
        RebelTable rebel = rebelRepository.findById(1L).get();

        assertEquals(1L, notification.getRebelId());
        assertEquals(2L, notification.getNotifierId());
        assertEquals(3l, count);
        assertEquals(true, rebel.isTraitor());

    }

}