package com.example.crudOperations.Exception;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(long id){
        super("super car not found with the id"+ id);
    }
}
