package com.chriscluney.reservationApp.repositories;


import com.chriscluney.reservationApp.entities.Review;
import com.chriscluney.reservationApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUserEquals(User user);
}
