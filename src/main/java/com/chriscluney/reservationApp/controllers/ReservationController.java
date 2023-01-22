package com.chriscluney.reservationApp.controllers;

import com.chriscluney.reservationApp.dtos.ReservationDto;
import com.chriscluney.reservationApp.dtos.ReviewDto;
import com.chriscluney.reservationApp.services.ReservationService;
import com.chriscluney.reservationApp.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {


    @Autowired
    private ReservationService reservationService;


    @GetMapping("/user/{userId}")
    public List<ReservationDto> getReservationsByUser(@PathVariable Long userId){
        return reservationService.getAllReservationsByUserId(userId);
    }


    @PostMapping("/user/{userId}")
    public void addReservation(@RequestBody ReservationDto reservationDto, @PathVariable Long userId){
        reservationService.addReservation(reservationDto, userId);
    }

    @DeleteMapping("/{reservationId}")
    public void deleteReservationById(@PathVariable Long reservationId){
        reservationService.deleteReservationById(reservationId);
    }

    @PutMapping
    public void updateReservation(@RequestBody ReservationDto reservationDto){
        reservationService.updateReservationById(reservationDto);
    }

    @GetMapping("/{reservationId}")
    public Optional<ReservationDto> getReservationById(@PathVariable Long reservationId){
        return reservationService.getReservationById(reservationId);
    }


}
