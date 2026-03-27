package com.example.restaurant_rating.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RatingRequestDto {
    private Long visitorId;
    private Long restaurantId;
    private Integer score;
    private String comment;

    public RatingRequestDto() {}

    public RatingRequestDto(Long visitorId, Long restaurantId, Integer score, String comment) {
        this.visitorId = visitorId;
        this.restaurantId = restaurantId;
        this.score = score;
        this.comment = comment;
    }

    public Long getVisitorId() { return visitorId; }
    public void setVisitorId(Long visitorId) { this.visitorId = visitorId; }

    public Long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}