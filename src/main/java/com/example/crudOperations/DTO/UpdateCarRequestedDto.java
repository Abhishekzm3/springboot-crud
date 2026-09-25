package com.example.crudOperations.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequestedDto{
    private String model;
    private Double price;
    private String email;
}
