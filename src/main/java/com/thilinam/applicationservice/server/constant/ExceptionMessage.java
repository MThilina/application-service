package com.thilinam.applicationservice.server.constant;

public enum ExceptionMessage {
    NOT_FOUND("NOT_FOUND","Related details not found"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR","Something went wrong and cause an INTERNAL_SERVER_ERROR"),
    FORBIDDEN("FORBIDDEN","User do not have access to execute this operation"),
    UNAUTHORIZED("UNAUTHORIZED","User UnAuthenticated");;

    private final String errorMessage;
    private final String errorCode;

     ExceptionMessage(String errorMessage,String errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
