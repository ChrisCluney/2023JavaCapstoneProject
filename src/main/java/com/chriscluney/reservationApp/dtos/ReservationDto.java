package com.chriscluney.reservationApp.dtos;

import com.chriscluney.reservationApp.entities.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto implements Serializable {

    private Long id;
    private String date;
    private String time;
    private UserDto userDto;

    public ReservationDto(Reservation reservation){
        if (reservation.getId() != null){
            this.id = reservation.getId();
        }
        if (reservation.getTime() != null){
            this.time = reservation.getTime();
        }
        if (reservation.getDate() != null){
            this.date = reservation.getDate();
        }
    }

}
