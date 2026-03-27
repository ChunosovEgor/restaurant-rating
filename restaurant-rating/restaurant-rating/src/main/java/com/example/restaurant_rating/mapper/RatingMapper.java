package com.example.restaurant_rating.mapper;

import com.example.restaurant_rating.dto.RatingRequestDto;
import com.example.restaurant_rating.dto.RatingResponseDto;
import com.example.restaurant_rating.entity.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {
    
    public Rating toEntity(RatingRequestDto dto) {
        if (dto == null) return null;
        return new Rating(
            null,
            dto.getVisitorId(),
            dto.getRestaurantId(),
            dto.getScore(),
            dto.getComment()
        );
    }
    
    public RatingResponseDto toResponseDto(Rating rating) {
        if (rating == null) return null;
        return new RatingResponseDto(
            rating.getId(),
            rating.getVisitorId(),
            rating.getRestaurantId(),
            rating.getScore(),
            rating.getComment()
        );
    }
}