package org.jinju.email.exceptions;

import org.jinju.global.exceptions.BadRequestException;

public class AuthCodeExpiredException extends BadRequestException {
    public AuthCodeExpiredException() {
        super("Expired.authCode");
        setErrorCode(true);
    }
}
