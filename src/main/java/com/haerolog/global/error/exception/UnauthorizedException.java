package com.haerolog.global.error.exception;

import org.springframework.http.HttpStatus;

/**
 * status -> 400
 */
public class UnauthorizedException extends HaerologException {

    private static final String MESSAGE = "인증되지 않은 사용자입니다.";

    public UnauthorizedException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.UNAUTHORIZED.value();
    }

}
