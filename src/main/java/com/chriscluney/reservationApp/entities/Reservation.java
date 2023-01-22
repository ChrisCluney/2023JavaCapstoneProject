package com.chriscluney.reservationApp.entities;


import com.chriscluney.reservationApp.dtos.ReservationDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String date;

    @Column(columnDefinition = "text")
    private String time;

    @ManyToOne
    @JsonBackReference
    private User user;


    public Reservation(ReservationDto reservationDto){
        if (reservationDto.getDate() != null){
            this.date = reservationDto.getDate();
        }
        if (reservationDto.getTime() != null){
            this.time =reservationDto.getTime();
        }
    }

}
