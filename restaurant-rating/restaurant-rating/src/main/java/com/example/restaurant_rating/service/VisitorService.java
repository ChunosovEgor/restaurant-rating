package com.example.restaurant_rating.service;

import com.example.restaurant_rating.dto.VisitorRequestDto;
import com.example.restaurant_rating.dto.VisitorResponseDto;
import com.example.restaurant_rating.entity.Visitor;
import com.example.restaurant_rating.mapper.VisitorMapper;
import com.example.restaurant_rating.repository.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;
    private final VisitorMapper visitorMapper;

    public VisitorService(VisitorRepository visitorRepository, VisitorMapper visitorMapper) {
        this.visitorRepository = visitorRepository;
        this.visitorMapper = visitorMapper;
    }

    public VisitorResponseDto createVisitor(VisitorRequestDto dto) {
        Visitor visitor = visitorMapper.toEntity(dto);
        Visitor saved = visitorRepository.save(visitor);
        return visitorMapper.toResponseDto(saved);
    }

    public VisitorResponseDto updateVisitor(Long id, VisitorRequestDto dto) {
        Visitor existing = visitorRepository.findAll().stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Посетитель не найден"));
        existing.setName(dto.getName());
        existing.setAge(dto.getAge());
        existing.setGender(dto.getGender());
        Visitor updated = visitorRepository.save(existing);
        return visitorMapper.toResponseDto(updated);
    }

    public void deleteVisitor(Long id) {
        visitorRepository.remove(id);
    }

    public List<VisitorResponseDto> getAllVisitors() {
        return visitorRepository.findAll().stream()
                .map(visitorMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public VisitorResponseDto getVisitorById(Long id) {
        Visitor visitor = visitorRepository.findAll().stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Посетитель не найден"));
        return visitorMapper.toResponseDto(visitor);
    }
}