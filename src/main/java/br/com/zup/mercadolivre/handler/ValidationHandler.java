package br.com.zup.mercadolivre.handler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler {

    private MessageSource messageSource;

    public ValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorsList> validationHandler(MethodArgumentNotValidException e) {

        ErrorsList erros = new ErrorsList(HttpStatus.BAD_REQUEST.value());

        e.getBindingResult().getGlobalErrors().forEach(error -> {
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            erros.addGlobalErrors(new Error(error.getObjectName(), mensagem));
        });

        e.getBindingResult().getFieldErrors().forEach(error -> {
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            erros.addFieldErrors(new Error(error.getField(), mensagem));
        });

        return ResponseEntity.badRequest().body(erros);
    }
}
