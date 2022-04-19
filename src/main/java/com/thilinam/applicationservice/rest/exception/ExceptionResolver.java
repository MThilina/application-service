package com.thilinam.applicationservice.rest.exception;

import com.thilinam.applicationservice.rest.dto.ExceptionDto;
import com.thilinam.applicationservice.rest.mapper.ExceptionMapper;
import com.thilinam.applicationservice.server.constant.ExceptionMessage;
import com.thilinam.applicationservice.server.constant.ExceptionType;
import com.thilinam.applicationservice.server.exception.ApplicationServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.file.AccessDeniedException;

import static com.thilinam.applicationservice.server.constant.ExceptionType.*;

@Log4j2
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionResolver {
    private static final String ERROR_MESSAGE = "An error occurred while processing the request :";

    private final ExceptionMapper exceptionMapper;

    @ExceptionHandler({ApplicationServiceException.class})
    public ResponseEntity<?> handelApplicationException(final ApplicationServiceException ex) {
        log.error("Error Message:{}, Error Code:{}", ex.getMessage(), ex.getExceptionType());
        return constructErrorResponse(ex);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(final Exception ex) {
        log.error(ERROR_MESSAGE, ex);

        final ApplicationServiceException exception =
                new ApplicationServiceException(ExceptionMessage.INTERNAL_SERVER_ERROR.getErrorCode(),ex.getLocalizedMessage(), INTERNAL_SERVER_ERROR);

        return getResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(final Exception ex) {
        log.error(ERROR_MESSAGE, ex);

        final ApplicationServiceException exception =
                new ApplicationServiceException(ExceptionMessage.FORBIDDEN.getErrorCode(),ex.getLocalizedMessage(), FORBIDDEN);

        return getResponseEntity(exception, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<?> handleUnAuthorizedException(final Exception ex) {
        log.error(ERROR_MESSAGE, ex);

        final ApplicationServiceException exception =
                new ApplicationServiceException(ExceptionMessage.UNAUTHORIZED.getErrorCode(),ex.getLocalizedMessage(),UNAUTHORIZED);

        return getResponseEntity(exception, HttpStatus.UNAUTHORIZED);

    }


    /**
     * @param ex
     * @return
     * @implNote Convert Customize Exception to Resolver
     */
    private ResponseEntity<?> constructErrorResponse(final ApplicationServiceException ex) {

        final ApplicationServiceException exception = buildServiceException(ex.getErrorCode(),ex.getMessage(), ex.getExceptionType());

        switch (ex.getExceptionType()) {
            case NOT_FOUND:
                return getResponseEntity(
                        exception, HttpStatus.NOT_FOUND
                );
            case BAD_REQUEST:
                return getResponseEntity(
                        exception, HttpStatus.BAD_REQUEST
                );
            case INTERNAL_SERVER_ERROR:
                return getResponseEntity(
                        exception, HttpStatus.INTERNAL_SERVER_ERROR
                );
            case FORBIDDEN:
                return getResponseEntity(
                        exception, HttpStatus.FORBIDDEN
                );
            default:
                return getResponseEntity(
                        exception, HttpStatus.GATEWAY_TIMEOUT
                );
        }

    }


    /**
     * @param message
     * @param auctionServiceExceptionType
     * @return
     * @implNote Create the return Exception from the customize exception
     */
    private ApplicationServiceException buildServiceException(String errorCode, String message, ExceptionType auctionServiceExceptionType) {
        final ApplicationServiceException exception = new ApplicationServiceException(errorCode,message, auctionServiceExceptionType);
        return exception;
    }


    /**
     * @param exception
     * @param status
     * @return
     * @implNote Create the return response to the client
     */
    private ResponseEntity<?> getResponseEntity(final ApplicationServiceException exception,
                                                final HttpStatus status) {

        ExceptionDto exceptionDTO = exceptionMapper.convertToRest(exception);
        return ResponseEntity.status(status.value()).body(exceptionDTO);
    }
}
