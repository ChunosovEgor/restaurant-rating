package com.example.restaurant_rating.dto;

import com.example.restaurant_rating.entity.CuisineType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RestaurantRequestDto {
    private String name;
    private String description;
    private CuisineType cuisineType;
    private BigDecimal averageBill;

    public RestaurantRequestDto() {}

    public RestaurantRequestDto(String name, String description, CuisineType cuisineType, BigDecimal averageBill) {
        this.name = name;
        this.description = description;
        this.cuisineType = cuisineType;
        this.averageBill = averageBill;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public CuisineType getCuisineType() { return cuisineType; }
    public void setCuisineType(CuisineType cuisineType) { this.cuisineType = cuisineType; }

    public BigDecimal getAverageBill() { return averageBill; }
    public void setAverageBill(BigDecimal averageBill) { this.averageBill = averageBill; }
}