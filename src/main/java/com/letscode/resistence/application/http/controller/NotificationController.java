package com.letscode.resistence.application.http.controller;

import com.letscode.resistence.domain.notification.NotificationTable;
import com.letscode.resistence.usecase.notification.NotifyRebelTraitorInput;
import com.letscode.resistence.usecase.notification.NotifyRebelTraitorUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resistence")
public class NotificationController {

    private final NotifyRebelTraitorUseCase notifyRebelTraitorUseCase;


    @Autowired
    public NotificationController(NotifyRebelTraitorUseCase notifyRebelTraitorUseCase){
        this.notifyRebelTraitorUseCase = notifyRebelTraitorUseCase;
    }

    @PostMapping(value = "/rebel/notification")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<String> notifyRebelTraitor(@RequestBody NotificationTable notificationTable){
        var input = new NotifyRebelTraitorInput(notificationTable.getRebelId(), notificationTable.getNotifierId());

        notifyRebelTraitorUseCase.handle(input);

        return ResponseEntity.status(HttpStatus.CREATED).body("ID: "+ notificationTable.getRebelId() + " notified " + notificationTable.getNotifierId());
    }

}
