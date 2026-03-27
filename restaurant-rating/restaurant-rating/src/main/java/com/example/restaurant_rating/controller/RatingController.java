package com.example.restaurant_rating.controller;

import com.example.restaurant_rating.dto.RatingRequestDto;
import com.example.restaurant_rating.dto.RatingResponseDto;
import com.example.restaurant_rating.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<RatingResponseDto> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingResponseDto> getRatingById(@PathVariable Long id) {
        RatingResponseDto dto = ratingService.getRatingById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<RatingResponseDto> createRating(@Valid @RequestBody RatingRequestDto dto) {
        RatingResponseDto created = ratingService.createRating(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingResponseDto> updateRating(@PathVariable Long id, @Valid @RequestBody RatingRequestDto dto) {
        RatingResponseDto updated = ratingService.updateRating(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }
}