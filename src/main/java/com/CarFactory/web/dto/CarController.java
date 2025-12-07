package com.CarFactory.web.dto;

import com.CarFactory.domain.Car;
import com.CarFactory.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService service;

    public CarController(CarService service) { this.service = service; }

    @GetMapping("/brand")
    public Iterable<Car> byBrand(@RequestParam String brand) {
      return service.findByBrand(brand);
    }

    @PostMapping
    public ResponseEntity<Car> create(@Valid @RequestBody Car car) {
        Car saved = service.create(car);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable long id, @RequestBody Car car) {
        return service.update(id, car).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam long id) {
      return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}