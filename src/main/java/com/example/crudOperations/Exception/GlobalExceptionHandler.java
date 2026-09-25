package com.example.crudOperations.Exception;

import com.example.crudOperations.Util.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ApiResponse> handelNotFound(CarNotFoundException ex){
        ApiResponse response= new ApiResponse();
        response.setMessage(ex.getMessage());
        response.setStatus("error");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


}
