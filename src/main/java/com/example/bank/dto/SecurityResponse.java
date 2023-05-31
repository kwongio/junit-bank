package com.example.bank.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityResponse {

    public static void sucess(HttpServletResponse response, Object dto)   {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDto<?> responseDto = new ResponseDto<>(1, "로그인성공", dto);
            String responseBody = objectMapper.writeValueAsString(responseDto);
            response.setStatus(401);
            response.getWriter().println(responseBody);
        } catch (Exception e) {
            log.error("에러발생");
        }
    }
    public static void unAuthentication(HttpServletResponse response, String message)   {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDto<?> responseDto = new ResponseDto<>(-1, message, null);
            String responseBody = objectMapper.writeValueAsString(responseDto);
            response.setStatus(401);
            response.getWriter().println(responseBody);
        } catch (Exception e) {
            log.error("에러발생");
        }
    }



    public static void unAuthorization(HttpServletResponse response, String message)   {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseDto<?> responseDto = new ResponseDto<>(-1, message, null);
            String responseBody = objectMapper.writeValueAsString(responseDto);
            response.setStatus(403);
            response.getWriter().println(responseBody);
        } catch (Exception e) {
            log.error("에러발생");
        }
    }
}
