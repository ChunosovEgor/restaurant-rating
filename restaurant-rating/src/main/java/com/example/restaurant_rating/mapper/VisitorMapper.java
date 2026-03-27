package com.example.restaurant_rating.mapper;

import com.example.restaurant_rating.dto.VisitorRequestDto;
import com.example.restaurant_rating.dto.VisitorResponseDto;
import com.example.restaurant_rating.entity.Visitor;
import org.springframework.stereotype.Component;

@Component
public class VisitorMapper {
    
    public Visitor toEntity(VisitorRequestDto dto) {
        if (dto == null) return null;
        return new Visitor(null, dto.getName(), dto.getAge(), dto.getGender());
    }
    
    public VisitorResponseDto toResponseDto(Visitor visitor) {
        if (visitor == null) return null;
        return new VisitorResponseDto(
            visitor.getId(),
            visitor.getName(),
            visitor.getAge(),
            visitor.getGender()
        );
    }
}