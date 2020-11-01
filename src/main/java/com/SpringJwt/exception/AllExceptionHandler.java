package com.SpringJwt.exception;

import com.SpringJwt.models.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AllExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger= LoggerFactory.getLogger(AllExceptionHandler.class);

    public ResponseEntity<Object> handleRecordNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getLocalizedMessage());
        logger.info("Exception message with date"+errorMessage);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
