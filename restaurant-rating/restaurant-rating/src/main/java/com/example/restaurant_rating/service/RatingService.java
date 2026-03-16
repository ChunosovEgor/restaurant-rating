package com.example.restaurant_rating.service;

import com.example.restaurant_rating.entity.Rating;
import com.example.restaurant_rating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RestaurantService restaurantService;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RestaurantService restaurantService) {
        this.ratingRepository = ratingRepository;
        this.restaurantService = restaurantService;
    }

    public Rating save(Rating rating) {
        Rating saved = ratingRepository.save(rating);
        recalculateRestaurantAverage(saved.getRestaurantId());
        return saved;
    }

    public void remove(Long id) {
        Rating rating = ratingRepository.findById(id);
        if (rating != null) {
            Long restaurantId = rating.getRestaurantId();
            ratingRepository.remove(id);
            recalculateRestaurantAverage(restaurantId);
        }
    }

    public List<Rating> findAll() {
        return ratingRepository.findAll();
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