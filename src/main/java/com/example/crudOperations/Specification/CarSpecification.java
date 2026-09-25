package com.example.crudOperations.Specification;

import com.example.crudOperations.Entity.Cars;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecification {
    public static Specification<Cars> hasModel(String model){
        return (root,query,cb)->{
            if(model == null || model.isEmpty())return null;
            return cb.like(cb.lower(root.get("model")),"%"+model.toLowerCase() +"%");
        };
    }
    public static Specification<Cars> hasMinPrice(Double minPrice){
        return(root,query,cb)->{
            if(minPrice ==null) return null;
            return cb.greaterThan(root.get("price"),minPrice);
        };
    }
    public  static Specification<Cars> hasMaxPrice(Double maxPrice){
        return(root,query,cb)->{
            if(maxPrice == null)return null;
            return cb.lessThan(root.get("price"),maxPrice);
        };
    }

}
