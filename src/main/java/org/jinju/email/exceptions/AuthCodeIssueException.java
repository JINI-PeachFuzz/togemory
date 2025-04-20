package org.jinju.email.exceptions;

import org.jinju.global.exceptions.BadRequestException;

public class AuthCodeIssueException extends BadRequestException {
    public AuthCodeIssueException() {
        super("Fail.authCode.issue");
        setErrorCode(true);
    }
}
