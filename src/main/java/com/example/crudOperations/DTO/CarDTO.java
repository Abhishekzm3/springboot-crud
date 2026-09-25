package com.example.crudOperations.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    private long id;
    private String model;
    private Double price;
    private String email;
}
