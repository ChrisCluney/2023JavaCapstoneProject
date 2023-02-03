package com.chriscluney.reservationApp.services;

import com.chriscluney.reservationApp.dtos.ReservationDto;
import com.chriscluney.reservationApp.entities.Reservation;
import com.chriscluney.reservationApp.entities.User;
import com.chriscluney.reservationApp.repositories.ReservationRepository;
import com.chriscluney.reservationApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public List<ReservationDto> getAllReservationsByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            List<Reservation> reservationList = reservationRepository.findAllByUserEquals(userOptional.get());
            return reservationList.stream().map(reservation -> new ReservationDto(reservation)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

//    @Override
//    public List<ReservationDto> getAllReservationsByUserId(Long userId) {
//        return null;
//    }

    @Override
    @Transactional
    public void addReservation(ReservationDto reservationDto, Long userId){
        Optional<User> userOptional = userRepository. findById(userId);
        Reservation reservation = new Reservation(reservationDto);
        userOptional.ifPresent(reservation::setUser);
        reservationRepository.saveAndFlush(reservation);
    }

    @Override
    @Transactional
    public void deleteReservationById(Long reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        reservationOptional.ifPresent(reservation -> reservationRepository.delete(reservation));
    }
    @Override
    @Transactional
    public void updateReservationById(ReservationDto reservationDto) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationDto.getId());
        reservationOptional.ifPresent(reservation -> {
            reservation.setDate(reservationDto.getDate());
            reservation.setTime(reservationDto.getTime());
            reservationRepository.saveAndFlush(reservation);
        });


    }

    @Override
    public Optional<ReservationDto> getReservationById(Long reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isPresent()){
            return Optional.of(new ReservationDto(reservationOptional.get()));

        }
        return Optional.empty();
    }
}
