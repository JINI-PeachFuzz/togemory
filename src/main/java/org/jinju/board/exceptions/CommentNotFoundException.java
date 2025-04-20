package org.jinju.board.exceptions;

import org.jinju.global.exceptions.scripts.AlertBackException;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends AlertBackException {
    public CommentNotFoundException() {
        super("NotFound.comment", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
