package com.CarFactory.service;

import com.CarFactory.domain.Car;
import com.CarFactory.repo.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CarService {
    private final CarRepository repo;

    public CarService(CarRepository repo) {
        this.repo = repo;
    }

    public Iterable<Car> list() { return repo.findAll(); }

    public Iterable<Car> findByBrand(String brand) {
    return repo.findByBrandIgnoreCase(brand);
    }


    public Optional<Car> find(long id) { return repo.findById(id); }

    public Car create(Car car) {
        return repo.save(car);
    }

    public Optional<Car> update(long id, Car patch) {
        return repo.findById(id).map(existing -> {
          if (patch.getBrand() != null) existing.setBrand(patch.getBrand());
            if (patch.getModel() != null) existing.setModel(patch.getModel());
            if (patch.getRegistrationNumber() != null) existing.setRegistrationNumber(patch.getRegistrationNumber());
            if (patch.getPrice() != null) existing.setPrice(patch.getPrice());
            return repo.save(existing);
        });
    }

    public boolean delete(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Car> updatePrice(long id, double newPrice) {
      return repo.findById(id).map(car -> {
        car.setPrice(newPrice);
        return repo.save(car);
    });
  }
}