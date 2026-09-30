package com.tickethub.common.exception;

/** 409: la operación rompe una regla de negocio (ej. transición de estado no permitida). */
public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException(String message) {
        super(message);
    }
}
