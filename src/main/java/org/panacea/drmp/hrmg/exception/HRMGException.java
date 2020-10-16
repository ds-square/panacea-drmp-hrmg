package org.panacea.drmp.hrmg.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HRMGException extends RuntimeException {

    protected Throwable throwable;

    public HRMGException(String message) {
        super(message);
    }

    public HRMGException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
        log.error("[HRMG]: ", message);
    }

    public Throwable getCause() {
        return throwable;
    }
}