package com.chriscluney.reservationApp.dtos;

import com.chriscluney.reservationApp.entities.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto implements Serializable {

    private Long id;
    private String body;
    private UserDto userDto;


    public ReviewDto(Review review){
        if (review.getId() != null){
            this.id = review.getId();
        }
        if (review.getBody() != null){
            this.body = review.getBody();
        }
    }

}
