package com.letscode.resistence.usecase;

import com.letscode.resistence.domain.rebel.*;
import com.letscode.resistence.usecase.exception.RebelAlreadyNotifiedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotifyRebelTraitorUseCaseTest {

    @Test
    public void shouldThrowExceptionWhenRebelAlreadyNotified(){
        NotificationRepository repository = new NotificationRepositoryInMemory();
        NotifyRebelTraitorUseCase useCase = new NotifyRebelTraitorUseCase(repository);

        NotificationTable notification = NotificationTable.builder()
                .rebelId(1L)
                .notifierId(2L)
                .build();

        repository.save(notification);

        NotificationRebelTraitorInput input = new NotificationRebelTraitorInput(1L, 2L);
        assertThrows(RebelAlreadyNotifiedException.class, () -> useCase.handle(input));
    }

    @Test
    public void shouldReportAsTraitorSuccessfully(){
        NotificationRepository repository = new NotificationRepositoryInMemory();
        NotifyRebelTraitorUseCase useCase = new NotifyRebelTraitorUseCase(repository);

        NotificationRebelTraitorInput input = new NotificationRebelTraitorInput(1L, 2L);
        useCase.handle(input);

        NotificationTable notification = repository.findByRebelIdAndNotifierIdExists(1L, 2l).get();

        assertEquals(1L, notification.getRebelId());
        assertEquals(2L, notification.getNotifierId());
    }

}