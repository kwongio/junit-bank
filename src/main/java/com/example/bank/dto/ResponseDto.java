package com.example.bank.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private final Integer code;
    private final String message;
    private final T data;
}
