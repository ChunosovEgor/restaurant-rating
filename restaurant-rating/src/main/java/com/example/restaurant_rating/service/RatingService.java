package com.example.restaurant_rating.service;

import com.example.restaurant_rating.dto.RatingRequestDto;
import com.example.restaurant_rating.dto.RatingResponseDto;
import com.example.restaurant_rating.entity.Rating;
import com.example.restaurant_rating.mapper.RatingMapper;
import com.example.restaurant_rating.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final RestaurantService restaurantService;

    public RatingService(RatingRepository ratingRepository, RatingMapper ratingMapper, RestaurantService restaurantService) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
        this.restaurantService = restaurantService;
    }

    public RatingResponseDto createRating(RatingRequestDto dto) {
        Rating rating = ratingMapper.toEntity(dto);
        Rating saved = ratingRepository.save(rating);
        recalculateRestaurantAverage(saved.getRestaurantId());
        return ratingMapper.toResponseDto(saved);
    }

    public RatingResponseDto updateRating(Long id, RatingRequestDto dto) {
        Rating existing = ratingRepository.findById(id);
        if (existing == null) {
            throw new RuntimeException("Отзыв не найден");
        }
        existing.setVisitorId(dto.getVisitorId());
        existing.setRestaurantId(dto.getRestaurantId());
        existing.setScore(dto.getScore());
        existing.setComment(dto.getComment());
        Rating updated = ratingRepository.save(existing);
        recalculateRestaurantAverage(updated.getRestaurantId());
        return ratingMapper.toResponseDto(updated);
    }

    public void deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id);
        if (rating != null) {
            Long restaurantId = rating.getRestaurantId();
            ratingRepository.remove(id);
            recalculateRestaurantAverage(restaurantId);
        }
    }

    public List<RatingResponseDto> getAllRatings() {
        return ratingRepository.findAll().stream()
                .map(ratingMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public RatingResponseDto getRatingById(Long id) {
        Rating rating = ratingRepository.findById(id);
        if (rating == null) {
            throw new RuntimeException("Отзыв не найден");
        }
        return ratingMapper.toResponseDto(rating);
    }

    private void recalculateRestaurantAverage(Long restaurantId) {
        List<Rating> ratings = ratingRepository.findAll().stream()
                .filter(r -> r.getRestaurantId().equals(restaurantId))
                .toList();

        if (ratings.isEmpty()) {
            restaurantService.setAverageRating(restaurantId, BigDecimal.ZERO);
            return;
        }

        double avg = ratings.stream()
                .mapToInt(Rating::getScore)
                .average()
                .orElse(0.0);

        BigDecimal average = BigDecimal.valueOf(avg).setScale(2, RoundingMode.HALF_UP);
        restaurantService.setAverageRating(restaurantId, average);
    }
}