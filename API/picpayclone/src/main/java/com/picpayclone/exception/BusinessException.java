package com.picpayclone.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -7507642233804283142L;

    // Constructor
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Exception exception) {
        super(message, exception);
    }

}
