package com.chriscluney.reservationApp.repositories;

import com.chriscluney.reservationApp.entities.Reservation;
import com.chriscluney.reservationApp.entities.User;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepositoryImplementation<Reservation, Long> {
    List<Reservation> findAllByUserEquals(User user);
}
