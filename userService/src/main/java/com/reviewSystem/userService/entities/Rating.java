package com.reviewSystem.userService.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    public String ratingId;
    public String userId;
    public String hotelId;
    public int rating;
    public String feedback;
    public Hotel hotel;
}
