package org.jinju.global.advices;

import lombok.RequiredArgsConstructor;
import org.jinju.global.exceptions.CommonException;
import org.jinju.global.libs.Utils;
import org.jinju.global.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
public class CommonRestControllerAdvice {

    private final Utils utils;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONData> errorHandler(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 기본 에러 코드 500

        Object message = e.getMessage();

        if (e instanceof CommonException commonException) {
            status = commonException.getStatus();

            Map<String, List<String>> errorMessages = commonException.getErrorMessages();
            if (errorMessages != null) {
                message = errorMessages;
            } else {
                message = commonException.isErrorCode() ? utils.getMessage((String)message) : message;
            }
        }

        JSONData data = new JSONData();
        data.setSuccess(false);
        data.setStatus(status);
        data.setMessage(message);

        e.printStackTrace();

        return ResponseEntity.status(status).body(data);
    }
}
