package com.example.restaurant_rating.config;

import com.example.restaurant_rating.entity.CuisineType;
import com.example.restaurant_rating.entity.Rating;
import com.example.restaurant_rating.entity.Restaurant;
import com.example.restaurant_rating.entity.Visitor;
import com.example.restaurant_rating.service.RestaurantService;
import com.example.restaurant_rating.service.RatingService;
import com.example.restaurant_rating.service.VisitorService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer {

    private final VisitorService visitorService;
    private final RestaurantService restaurantService;
    private final RatingService ratingService;

    @Autowired
    public DataInitializer(VisitorService visitorService, RestaurantService restaurantService, RatingService ratingService) {
        this.visitorService = visitorService;
        this.restaurantService = restaurantService;
        this.ratingService = ratingService;
    }

    @PostConstruct
    public void init() {
        Visitor visitor1 = new Visitor(null, "Иван", 25, "М");
        Visitor visitor2 = new Visitor(null, "Мария", 30, "Ж");
        Visitor visitor3 = new Visitor(null, null, 22, "Ж");
        visitorService.save(visitor1);
        visitorService.save(visitor2);
        visitorService.save(visitor3);

        Restaurant rest1 = new Restaurant(null, "У Ивана", "Уютное место", CuisineType.RUSSIAN, new BigDecimal("1500"), BigDecimal.ZERO);
        Restaurant rest2 = new Restaurant(null, "Pasta Mia", "Итальянская кухня", CuisineType.ITALIAN, new BigDecimal("2000"), BigDecimal.ZERO);
        Restaurant rest3 = new Restaurant(null, "Wok", "Китайский фастфуд", CuisineType.CHINESE, new BigDecimal("800"), BigDecimal.ZERO);
        restaurantService.save(rest1);
        restaurantService.save(rest2);
        restaurantService.save(rest3);

        ratingService.save(new Rating(null, visitor1.getId(), rest1.getId(), 5, "Отлично!"));
        ratingService.save(new Rating(null, visitor2.getId(), rest1.getId(), 4, "Хорошо, но долго"));
        ratingService.save(new Rating(null, visitor3.getId(), rest2.getId(), 5, "Лучшая паста"));
        ratingService.save(new Rating(null, visitor1.getId(), rest2.getId(), 3, "Слишком дорого"));
        ratingService.save(new Rating(null, visitor2.getId(), rest3.getId(), 4, "Вкусно и быстро"));

        System.out.println("=== Посетители ===");
        visitorService.findAll().forEach(System.out::println);

        System.out.println("=== Рестораны ===");
        restaurantService.findAll().forEach(System.out::println);

        System.out.println("=== Оценки ===");
        ratingService.findAll().forEach(System.out::println);
    }
}