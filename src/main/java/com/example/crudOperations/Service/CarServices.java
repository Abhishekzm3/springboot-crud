package com.example.crudOperations.Service;

import com.example.crudOperations.DTO.AddCarRequestDto;
import com.example.crudOperations.DTO.CarDTO;
import com.example.crudOperations.DTO.UpdateCarRequestedDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarServices {

        Page<CarDTO> getAllCars(String name,Double minPrice, Double maxPrice,Pageable pageable);

        CarDTO getCarById(long id);

        CarDTO createNewCar(@Valid AddCarRequestDto addCarRequestDto);

        void deleteCarById(long id);

        CarDTO updateCar(long id, @Valid AddCarRequestDto addCarRequestDto);

        CarDTO updatePartialCar(long id, UpdateCarRequestedDto update);
}

