package com.CarFactory.web.dto;


import jakarta.validation.constraints.NotBlank;


public class CarRequest {

@NotBlank
private String brand;

@NotBlank
private String model;

@NotBlank
private String registrationNumber;

@NotBlank
private double price;

public String getbrand() { return brand; }
public void setBrand(String brand) { this.brand = brand; }

public String getModel() { return model; }
public void setModel(String model) { this.model = model; }


public String getRegistrationNumber() { return registrationNumber; }
public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }


public Double getStatus() { return price; }
public void setPrice(Double price) { this.price = price; }
}