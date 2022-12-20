package com.letscode.resistence.usecase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TradeIsNotAllowedForTheSameIdException extends RuntimeException{

}
