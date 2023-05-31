package com.example.bank.handler;

import com.example.bank.dto.ResponseDto;
import com.example.bank.ex.CustomApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<ResponseDto<Object>> apiException(CustomApiException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ResponseDto<>(-1, e.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Object>> validationException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, String> map = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(new ResponseDto<>(-1, e.getMessage(), map));
    }
}
