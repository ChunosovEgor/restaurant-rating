package com.example.restaurant_rating.controller;

import com.example.restaurant_rating.dto.VisitorRequestDto;
import com.example.restaurant_rating.dto.VisitorResponseDto;
import com.example.restaurant_rating.service.VisitorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class VisitorController {
    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping
    public List<VisitorResponseDto> getAllVisitors() {
        return visitorService.getAllVisitors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitorResponseDto> getVisitorById(@PathVariable Long id) {
        VisitorResponseDto dto = visitorService.getVisitorById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<VisitorResponseDto> createVisitor(@Valid @RequestBody VisitorRequestDto dto) {
        VisitorResponseDto created = visitorService.createVisitor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitorResponseDto> updateVisitor(@PathVariable Long id, @Valid @RequestBody VisitorRequestDto dto) {
        VisitorResponseDto updated = visitorService.updateVisitor(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitor(@PathVariable Long id) {
        visitorService.deleteVisitor(id);
        return ResponseEntity.noContent().build();
    }
}