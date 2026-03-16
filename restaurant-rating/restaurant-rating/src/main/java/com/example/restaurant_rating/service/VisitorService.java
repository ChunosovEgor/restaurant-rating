package com.example.restaurant_rating.service;

import com.example.restaurant_rating.entity.Visitor;
import com.example.restaurant_rating.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public Visitor save(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public void remove(Long id) {
        visitorRepository.remove(id);
    }

    public List<Visitor> findAll() {
        return visitorRepository.findAll();
    }
}