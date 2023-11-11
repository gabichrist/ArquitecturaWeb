package com.usuario.security.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@Getter
@RequiredArgsConstructor
public class ExpiredTokenException extends RuntimeException {

    private final EnumJWTException code;
    private final String message;

}
