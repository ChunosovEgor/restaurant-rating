package com.example.restaurant_rating.dto;

public class RatingResponseDto {
    private Long id;
    private Long visitorId;
    private Long restaurantId;
    private Integer score;
    private String comment;

    public RatingResponseDto() {}

    public RatingResponseDto(Long id, Long visitorId, Long restaurantId, Integer score, String comment) {
        this.id = id;
        this.visitorId = visitorId;
        this.restaurantId = restaurantId;
        this.score = score;
        this.comment = comment;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getVisitorId() { return visitorId; }
    public void setVisitorId(Long visitorId) { this.visitorId = visitorId; }

    public Long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}