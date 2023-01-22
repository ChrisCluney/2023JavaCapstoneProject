package com.chriscluney.reservationApp.services;

import com.chriscluney.reservationApp.dtos.ReservationDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<ReservationDto> getAllReservationByUserId(Long userId);

    List<ReservationDto> getAllReservationsByUserId(Long userId);

    @Transactional
    void addReservation(ReservationDto reservationDto, Long userId);

    @Transactional
    void deleteReservationById(Long reservationId);

    @Transactional
    void updateReservationById(ReservationDto reservationDto);

    Optional<ReservationDto> getReservationById(Long reservationId);
}
