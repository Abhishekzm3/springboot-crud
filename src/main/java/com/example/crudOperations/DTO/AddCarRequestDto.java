package com.example.crudOperations.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// this is used for (POST / PUT)
@Data
@NoArgsConstructor
public class AddCarRequestDto {

    @NotBlank(message = "Model is required")
    @Size(min = 3, max = 10, message = "Model must be between 3 to 10 characters")
    private String model;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than 0")
    private Double price;

    @Email(message = "Invalid email format")
    private String email;

}
