package com.chriscluney.reservationApp.controllers;

import com.chriscluney.reservationApp.dtos.ReviewDto;
import com.chriscluney.reservationApp.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/user/{userId}")
    public List<ReviewDto> getReviewsByUser(@PathVariable Long userId){
        return reviewService.getAllReviewsByUserId(userId);
    }


    @PostMapping("/user/{userId}")
    public void addReview(@RequestBody ReviewDto reviewDto,@PathVariable Long userId){
        reviewService.addReview(reviewDto, userId);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReviewById(@PathVariable Long reviewId){
        reviewService.deleteReviewById(reviewId);
    }

    @PutMapping
    public void updateReviewById(@RequestBody ReviewDto reviewDto){
        reviewService.updateReviewById(reviewDto);
    }

    @GetMapping("/{reviewId}")
    public Optional<ReviewDto> getReviewById(@PathVariable Long reviewId){
        return reviewService.getReviewById(reviewId);
    }


}
