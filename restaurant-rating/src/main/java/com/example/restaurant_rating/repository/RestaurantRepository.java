package com.example.restaurant_rating.repository;

import com.example.restaurant_rating.entity.Restaurant;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RestaurantRepository {
    private final ConcurrentHashMap<Long, Restaurant> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Restaurant save(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            restaurant.setId(idGenerator.getAndIncrement());
        }
        storage.put(restaurant.getId(), restaurant);
        return restaurant;
    }

    public void remove(Long id) {
        storage.remove(id);
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(storage.values());
    }
}