package com.example.swtod.configs.exception;

import com.example.swtod.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Consumer;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException(String message) {
        super(message);
        log.info(message);
    }
}
