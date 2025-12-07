package com.CarFactory.web.dto;

import com.CarFactory.domain.Car;
import com.CarFactory.service.CarService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;




@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService service;

    public CarController(CarService service) { this.service = service; }

    @GetMapping("/brand")
    public ResponseEntity<?> byBrand(@RequestParam String brand) {

        List<Car> cars = service.findByBrand(brand);

        if (cars.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(Map.of("error", "No cars found for brand: " + brand));
        }

        return ResponseEntity.ok(cars);
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
    public ResponseEntity<Map<String, String>> delete(@RequestParam long id) {
      boolean deleted = service.delete(id);

      if (deleted) {
        return ResponseEntity.ok(
          Map.of("message", "Car deleted successfully")
          );
        } else {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of("error", "Car not found")
          );
        }
    }

}