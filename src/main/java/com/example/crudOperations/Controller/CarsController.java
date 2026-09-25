package com.example.crudOperations.Controller;

import com.example.crudOperations.DTO.AddCarRequestDto;
import com.example.crudOperations.DTO.CarDTO;
import com.example.crudOperations.DTO.UpdateCarRequestedDto;
import com.example.crudOperations.Service.CarServices;
import com.example.crudOperations.Util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")

public class CarsController {

    private final CarServices carService;

    @GetMapping    // getAll
    public ResponseEntity<Page<CarDTO>> getAllCars(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10")int pageSize,
            @RequestParam(defaultValue = "id")String sortBy,
            @RequestParam(defaultValue = "asc")String sortDir,
            @RequestParam(required = false)String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice){

        Sort sort= sortDir.equalsIgnoreCase("ASC")?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        return ResponseEntity.ok(carService.getAllCars(name,minPrice,maxPrice,pageable));
    }
    @GetMapping("/{id}")  // getById
    public ResponseEntity<CarDTO> getCarById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(carService.getCarById(id));
    }
    @PostMapping
    public ResponseEntity<CarDTO> createNewCar(@RequestBody @Valid AddCarRequestDto addCarRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carService.createNewCar(addCarRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCarById(@PathVariable long id){
        carService.deleteCarById(id);

        ApiResponse response=new ApiResponse();
        response.setMessage("Deleted successfully");
        response.setStatus("success");
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable long id,
                                            @RequestBody @Valid AddCarRequestDto addCarRequestDto){
        return ResponseEntity.ok(carService.updateCar(id,addCarRequestDto));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<CarDTO> updatePartialCar(@PathVariable long id,
                                                   @RequestBody UpdateCarRequestedDto update){
        return ResponseEntity.ok(carService.updatePartialCar(id,update));
    }






}
