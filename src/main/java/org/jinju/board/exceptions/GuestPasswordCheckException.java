package org.jinju.board.exceptions;

import org.jinju.global.exceptions.BadRequestException;

public class GuestPasswordCheckException extends BadRequestException {
    public GuestPasswordCheckException() {
        super("Required.guestPassword");
        setErrorCode(true);
    }
}
