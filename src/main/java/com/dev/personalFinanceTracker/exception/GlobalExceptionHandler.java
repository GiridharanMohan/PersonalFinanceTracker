package com.dev.personalFinanceTracker.exception;

import com.dev.personalFinanceTracker.model.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = RuntimeException.class)
    private ResponseEntity<ResponseDto<String>> handleRuntimeException(RuntimeException e){
        log.error("RuntimeException occurred");
        ResponseDto<String> response = new ResponseDto<>(false, e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<ResponseDto<String>> handleUsernameNotFoundException(UsernameNotFoundException e){
        log.debug("UsernameNotFoundException occurred");
        ResponseDto<String> response = new ResponseDto<>(false,e.getMessage(),null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(exception = DataNotFoundException.class)
    private ResponseEntity<ResponseDto<String>> handleRuntimeException(DataNotFoundException e){
        log.error("DataNotFoundException occurred");
        ResponseDto<String> response = new ResponseDto<>(false, e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
