package com.example.crudOperations.Repository;

import com.example.crudOperations.Entity.Cars;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsRepository extends JpaRepository<Cars,Long>, JpaSpecificationExecutor<Cars> {
    Page<Cars> findByModelContainingIgnoreCase(String model, Pageable pageable);

}
