package org.jinju.email.exceptions;

import org.jinju.global.exceptions.BadRequestException;

public class AuthCodeMismatchException extends BadRequestException {
    public AuthCodeMismatchException() {
        super("Mismatch.authCode");
        setErrorCode(true);
    }
}
