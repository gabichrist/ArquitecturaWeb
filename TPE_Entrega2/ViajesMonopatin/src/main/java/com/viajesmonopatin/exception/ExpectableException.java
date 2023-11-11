package com.viajesmonopatin.exception;

@SuppressWarnings("serial")
public class ExpectableException extends Exception {
    public ExpectableException() {
        super();
    }

    public ExpectableException(String mensaje) {
        super(mensaje);
    }

    public ExpectableException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
