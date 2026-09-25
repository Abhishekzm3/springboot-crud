package com.example.crudOperations.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cars")
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String model;
    private Double price;
    private String email;
    private String address;

}
