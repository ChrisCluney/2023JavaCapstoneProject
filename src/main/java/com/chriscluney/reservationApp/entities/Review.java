package com.chriscluney.reservationApp.entities;

import com.chriscluney.reservationApp.dtos.ReviewDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Review(ReviewDto reviewDto){
        if (reviewDto.getBody() != null){
            this.body = reviewDto.getBody();
        }
    }
}
