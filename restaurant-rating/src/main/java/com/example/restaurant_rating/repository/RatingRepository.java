package com.example.restaurant_rating.repository;

import com.example.restaurant_rating.entity.Rating;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RatingRepository {
    private final ConcurrentHashMap<Long, Rating> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Rating save(Rating rating) {
        if (rating.getId() == null) {
            rating.setId(idGenerator.getAndIncrement());
        }
        storage.put(rating.getId(), rating);
        return rating;
    }

    public void remove(Long id) {
        storage.remove(id);
    }

    public List<Rating> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Rating findById(Long id) {
        return storage.get(id);
    }
}