package com.github.marcoscouto.instapetzup.controllers.handler;

import com.github.marcoscouto.instapetzup.exceptions.NotFoundException;
import com.github.marcoscouto.instapetzup.exceptions.OperationNotAllowed;
import com.github.marcoscouto.instapetzup.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerHandler {

    LocalDateTime instant = LocalDateTime.now(ZoneId.of("UTC"));

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardError handleNotFoundException(NotFoundException e){
        return new StandardError("Erro - Entidade não encontrada", Arrays.asList(e.getMessage()), instant);
    }

    @ExceptionHandler(OperationNotAllowed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardError handleOperationNotAllowed(OperationNotAllowed e){
        return new StandardError("Erro - Operação não permitida", Arrays.asList(e.getMessage()), instant);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardError handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return new StandardError("Erro - Validação de dados", errors, instant);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardError handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new StandardError("Erro - Validação de dados", Arrays.asList(e.getMostSpecificCause().getMessage()), instant);
    }

}
