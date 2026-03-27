package com.example.restaurant_rating.mapper;

import com.example.restaurant_rating.dto.RestaurantRequestDto;
import com.example.restaurant_rating.dto.RestaurantResponseDto;
import com.example.restaurant_rating.entity.Restaurant;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class RestaurantMapper {
    
    public Restaurant toEntity(RestaurantRequestDto dto) {
        if (dto == null) return null;
        return new Restaurant(
            null,
            dto.getName(),
            dto.getDescription(),
            dto.getCuisineType(),
            dto.getAverageBill(),
            BigDecimal.ZERO
        );
    }
    
    public RestaurantResponseDto toResponseDto(Restaurant restaurant) {
        if (restaurant == null) return null;
        return new RestaurantResponseDto(
            restaurant.getId(),
            restaurant.getName(),
            restaurant.getDescription(),
            restaurant.getCuisineType(),
            restaurant.getAverageBill(),
            restaurant.getAverageRating()
        );
    }
}