package com.example.bank.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

//@Component
//@Aspect
public class CustomValidationAdvice {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {

    }


    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMapping() {

    }


    @Around("postMapping() || putMapping()") //joinPoint 전후 제어
    public Object validationAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs(); //joinPoint의 매개변수
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();
                    bindingResult.getFieldErrors().forEach(fieldError -> {
                        errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    });

                }
            }
        }
        return joinPoint.proceed();
    }
}
