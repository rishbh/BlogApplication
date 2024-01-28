package com.blogapplication.blog.exceptions;

import com.blogapplication.blog.entities.APIResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

     @ExceptionHandler(ResourceNotFoundException.class)
     public ResponseEntity<APIResponse> requestNotFoundHandler(ResourceNotFoundException exception){
            APIResponse apiResponse = new APIResponse(exception.getMessage(), Boolean.TRUE);
                 return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
     }
     @ExceptionHandler(ConstraintViolationException.class)
     public ResponseEntity<Map<String,String>>constraintsViolationExceptionHandler(ConstraintViolationException ex){

         Map<String,String>invalidArgsMessages=new HashMap<>();
         ex.getConstraintViolations().stream().forEach(error->{
             String errorMessage=error.getMessage();
             String pathProperty=error.getPropertyPath().toString();

             invalidArgsMessages.put(pathProperty,errorMessage);

         });
         return new ResponseEntity<>(invalidArgsMessages,HttpStatus.BAD_REQUEST);

     }





}
