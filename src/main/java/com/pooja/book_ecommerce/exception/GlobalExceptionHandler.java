package com.pooja.book_ecommerce.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleException(BookNotFoundException e, WebRequest request){
        ErrorDetails error=new ErrorDetails(LocalDate.now(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler

    public ResponseEntity<ErrorDetails> handleException(CustomerNotFoundException e, WebRequest request){
        ErrorDetails error=new ErrorDetails(LocalDate.now(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleException(CartException e, WebRequest request){
        ErrorDetails error=new ErrorDetails(LocalDate.now(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status,
            WebRequest request){
        ErrorDetails error=new ErrorDetails(LocalDate.now(),
                "Total Errors :"+e.getErrorCount()+" First Error :"+e.getFieldError().getDefaultMessage(),request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

}
