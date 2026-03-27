package com.example.restaurant_rating.repository;

import com.example.restaurant_rating.entity.Visitor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class VisitorRepository {
    private final ConcurrentHashMap<Long, Visitor> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Visitor save(Visitor visitor) {
        if (visitor.getId() == null) {
            visitor.setId(idGenerator.getAndIncrement());
        }
        storage.put(visitor.getId(), visitor);
        return visitor;
    }

    public void remove(Long id) {
        storage.remove(id);
    }

    public List<Visitor> findAll() {
        return new ArrayList<>(storage.values());
    }
}