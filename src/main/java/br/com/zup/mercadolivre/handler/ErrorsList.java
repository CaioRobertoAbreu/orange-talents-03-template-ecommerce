package br.com.zup.mercadolivre.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ErrorsList {

    private LocalDateTime instante;
    private int httpStatus;
    private List<Error> globalErrors = new ArrayList<>();
    private List<Error> fieldErrors = new ArrayList<>();

    public ErrorsList(int httpStatus) {
        this.instante = LocalDateTime.now();
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public List<Error> getGlobalErrors() {
        return globalErrors;
    }

    public List<Error> getFieldErrors() {
        return fieldErrors;
    }

    public void addGlobalErrors(Error globalError) {
        this.globalErrors.add(globalError);
    }

    public void addFieldErrors(Error fieldError) {
        this.fieldErrors.add(fieldError);
    }
}
