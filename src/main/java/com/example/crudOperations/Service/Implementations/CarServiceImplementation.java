package com.example.crudOperations.Service.Implementations;

import com.example.crudOperations.DTO.AddCarRequestDto;
import com.example.crudOperations.DTO.CarDTO;
import com.example.crudOperations.DTO.UpdateCarRequestedDto;
import com.example.crudOperations.Entity.Cars;
import com.example.crudOperations.Exception.CarNotFoundException;
import com.example.crudOperations.Repository.CarsRepository;
import com.example.crudOperations.Service.CarServices;
import com.example.crudOperations.Specification.CarSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import static org.springframework.data.jpa.domain.Specification.*;

@Service
@AllArgsConstructor
public class CarServiceImplementation implements CarServices {

    private final CarsRepository carRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<CarDTO> getAllCars(String name,Double minPrice,Double maxPrice,Pageable pageable) {
        Specification<Cars> spec= Specification
                .where(CarSpecification.hasModel(name))
                .and(CarSpecification.hasMinPrice(minPrice))
                .and(CarSpecification.hasMaxPrice(maxPrice));

        Page<Cars> carPage;
        if(name != null && !name.isEmpty()){
            carPage = carRepository.findByModelContainingIgnoreCase(name,pageable);
        }else{
            carPage= carRepository.findAll(pageable);
        }
                return carPage.map(Car-> new CarDTO(
                        Car.getId(),
                        Car.getModel(),
                        Car.getPrice(),
                        Car.getEmail()
                ));
    }// or else use modelMapper

    /*
             private StudentDto mapToDto(Cars cars){
                return modelMapper.map(Cars, CarDto.class);
            }*/
    @Override
    public CarDTO getCarById(long id) {
        Cars car = carRepository.findById(id)
                .orElseThrow(()-> new CarNotFoundException(id));
        return modelMapper.map(car, CarDTO.class);
    }

    @Override
    public CarDTO createNewCar(AddCarRequestDto addCarRequestDto) {
        Cars car=modelMapper.map(addCarRequestDto, Cars.class);
        Cars savedCar =carRepository.save(car);
        return modelMapper.map(savedCar, CarDTO.class);
    }

    @Override
    public void deleteCarById(long id) {
        Cars car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        carRepository.delete(car);
    }

    @Override
    public CarDTO updateCar(long id, AddCarRequestDto addCarRequestDto) {
        Cars car= carRepository.findById(id)
                .orElseThrow(()->new CarNotFoundException(id));
        modelMapper.map(addCarRequestDto, car);
        car=carRepository.save(car);
        return modelMapper.map(car,CarDTO.class);
    }

    @Override
    public CarDTO updatePartialCar(long id, UpdateCarRequestedDto update) {
        Cars car= carRepository.findById(id)
                .orElseThrow(()-> new CarNotFoundException(id));
        if(update.getModel() != null){
            car.setModel(update.getModel());
        }
        if(update.getPrice() != null){
            car.setPrice(update.getPrice());
        }
        if(update.getEmail() != null){
            car.setEmail(update.getEmail());
        }
        Cars updated = carRepository.save(car);
        return modelMapper.map(updated,CarDTO.class);
    }
}
