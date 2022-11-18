package com.example.swtod.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException(String message) {
        super(message);
        log.info(message);
    }
}