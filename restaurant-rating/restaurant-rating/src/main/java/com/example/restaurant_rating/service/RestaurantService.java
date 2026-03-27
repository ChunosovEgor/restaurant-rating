package com.example.restaurant_rating.service;

import com.example.restaurant_rating.dto.RestaurantRequestDto;
import com.example.restaurant_rating.dto.RestaurantResponseDto;
import com.example.restaurant_rating.entity.Restaurant;
import com.example.restaurant_rating.mapper.RestaurantMapper;
import com.example.restaurant_rating.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public RestaurantResponseDto createRestaurant(RestaurantRequestDto dto) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        Restaurant saved = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponseDto(saved);
    }

    public RestaurantResponseDto updateRestaurant(Long id, RestaurantRequestDto dto) {
        Restaurant existing = restaurantRepository.findAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ресторан не найден"));
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setCuisineType(dto.getCuisineType());
        existing.setAverageBill(dto.getAverageBill());
        Restaurant updated = restaurantRepository.save(existing);
        return restaurantMapper.toResponseDto(updated);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.remove(id);
    }

    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public RestaurantResponseDto getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ресторан не найден"));
        return restaurantMapper.toResponseDto(restaurant);
    }

    public void setAverageRating(Long restaurantId, BigDecimal averageRating) {
        Restaurant restaurant = restaurantRepository.findAll().stream()
                .filter(r -> r.getId().equals(restaurantId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ресторан не найден"));
        restaurant.setAverageRating(averageRating);
    }
}