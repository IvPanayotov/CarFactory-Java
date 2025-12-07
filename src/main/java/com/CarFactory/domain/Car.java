package com.CarFactory.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, message = "Brand must be at least 2 characters")
    private String brand;
    
    @Column(nullable = false)
    @Size(min = 2, message = "Model must be at least 2 characters")
    private String model;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[A-Z]{2} \\d{4} [A-Z]{2}$",message = "Registration number must follow the format 'AA 1234 BB'")
    private String registrationNumber;

    @Column(nullable = false)
    @Min(value = 1, message = "Price must be more than 0")
    private Double price;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    public Car() {}

    public Car(Long id, String brand,String model, String registrationNumber, double price, Instant createdAt) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.price = price;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) this.createdAt = Instant.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}