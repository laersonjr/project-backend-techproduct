package com.laerson.techsolutio.techproduct.api.exceptionhandler;

import com.laerson.techsolutio.techproduct.core.security.exception.TokenInvalidException;
import com.laerson.techsolutio.techproduct.core.security.exception.TokenNotFoundException;
import com.laerson.techsolutio.techproduct.domain.exception.IncorrectPasswordException;
import com.laerson.techsolutio.techproduct.domain.exception.ProductNotFoundException;
import com.laerson.techsolutio.techproduct.domain.exception.UserNotFoundException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorDetails.ProblemError> errorList = getProblemErrors(ex.getBindingResult().getAllErrors());
        ErrorDetails errorDetails = getErrorDetails(status, errorList);
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDetails errorDetails = getErrorDetail(status, ex.getMessage());
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    private ResponseEntity<Object> handleIncorrectPasswordException(IncorrectPasswordException ex, WebRequest request){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorDetails errorDetails = getErrorDetail(status, ex.getMessage());
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDetails errorDetails = getErrorDetail(status, ex.getMessage());
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    private ResponseEntity<Object> handleTokenNotFoundException(TokenNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorDetails errorDetails = getErrorDetail(status, ex.getMessage());
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(TokenInvalidException.class)
    private ResponseEntity<Object> handleTokenInvalidException(TokenInvalidException ex, WebRequest request){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorDetails errorDetails = getErrorDetail(status, ex.getMessage());
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(MalformedJwtException.class)
    private ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException ex, WebRequest request){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorDetails errorDetails = getErrorDetail(status, messageSource.getMessage("token-invalid", null, LocaleContextHolder.getLocale()));
        return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), status, request);
    }

    private ErrorDetails getErrorDetails(HttpStatusCode status, List<ErrorDetails.ProblemError> errorList) {
        return new ErrorDetails(
                status.value(),
                messageSource.getMessage("invalid-fields", null, LocaleContextHolder.getLocale()),
                errorList);
    }

    private ErrorDetails getErrorDetail(HttpStatus status, String message) {
        return new ErrorDetails(
                status.value(),
                message
        );
    }

    private List<ErrorDetails.ProblemError> getProblemErrors(List<ObjectError> allErrors) {
        return allErrors.stream()
                .map(objectError -> new ErrorDetails.ProblemError(
                        ((FieldError) objectError).getField(),
                        messageSource.getMessage(objectError, LocaleContextHolder.getLocale()),
                        objectError.toString()))
                .collect(Collectors.toList());
    }
}
