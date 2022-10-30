package com.grabas.autenticacaoapi.comum.exception.controller;

import com.grabas.autenticacaoapi.comum.exception.model.ExResponse;
import com.grabas.autenticacaoapi.comum.exception.model.ValidacaoException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExHandlerController {

    @ExceptionHandler(ValidacaoException.class)
    public ExResponse getErrorMessage(ValidacaoException ex) {
        return new ExResponse(ex.getMessage());
    }
}
