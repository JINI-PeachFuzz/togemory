package org.jinju.message.exceptions;

import org.jinju.global.exceptions.scripts.AlertBackException;
import org.springframework.http.HttpStatus;

public class MessageNotFoundException extends AlertBackException {

    public MessageNotFoundException() {
        super("NotFound.message", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
