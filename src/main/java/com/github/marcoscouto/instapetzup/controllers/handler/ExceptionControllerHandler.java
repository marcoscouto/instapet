package com.github.marcoscouto.instapetzup.controllers.handler;

import com.github.marcoscouto.instapetzup.exceptions.NotFoundException;
import com.github.marcoscouto.instapetzup.exceptions.OperationNotAllowed;
import com.github.marcoscouto.instapetzup.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

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

}
