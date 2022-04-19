package com.thilinam.applicationservice.server.exception;

import com.thilinam.applicationservice.server.constant.ExceptionType;
import lombok.Getter;

public class ApplicationServiceException extends RuntimeException{
    @Getter private final ExceptionType exceptionType;
    @Getter private final String errorCode;


    public ApplicationServiceException(final String errorCode,final String message,final ExceptionType exceptionType){
        super(message);
        this.errorCode = errorCode;
        this.exceptionType = exceptionType;
    }

}
