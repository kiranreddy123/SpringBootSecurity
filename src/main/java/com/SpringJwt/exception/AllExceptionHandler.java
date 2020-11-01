package com.SpringJwt.exception;

import com.SpringJwt.models.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AllExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseEntity<Object> handleRecordNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getLocalizedMessage());
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
