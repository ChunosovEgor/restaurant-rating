package com.example.restaurant_rating.entity;

public class Rating {
    private Long id;
    private Long visitorId;
    private Long restaurantId;
    private int score;
    private String comment;

    public Rating() {}

    public Rating(Long id, Long visitorId, Long restaurantId, int score, String comment) {
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

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", visitorId=" + visitorId +
                ", restaurantId=" + restaurantId +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}