package com.chriscluney.reservationApp.services;

import com.chriscluney.reservationApp.dtos.ReviewDto;
import com.chriscluney.reservationApp.entities.Review;
import com.chriscluney.reservationApp.entities.User;
import com.chriscluney.reservationApp.repositories.ReviewRepository;
import com.chriscluney.reservationApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public List<ReviewDto> getAllReviewsByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            List<Review> reviewList = reviewRepository.findAllByUserEquals(userOptional.get());
            return reviewList.stream().map(review -> new ReviewDto(review)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    @Override
    @Transactional
    public void addReview(ReviewDto reviewDto, Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        Review review = new Review(reviewDto);
        userOptional.ifPresent(review::setUser);
        reviewRepository.saveAndFlush(review);
    }

    @Override
    @Transactional
    public void deleteReviewById(Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        reviewOptional.ifPresent(review -> reviewRepository.delete(review));
    }
    @Override
    @Transactional
    public void updateReviewById(ReviewDto reviewDto) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewDto.getId());
        reviewOptional.ifPresent(review -> {
            review.setBody(reviewDto.getBody());
            reviewRepository.saveAndFlush(review);
        });


    }

    @Override
    public Optional<ReviewDto> getReviewById(Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()){
            return Optional.of(new ReviewDto(reviewOptional.get()));

        }
        return Optional.empty();
    }
}
