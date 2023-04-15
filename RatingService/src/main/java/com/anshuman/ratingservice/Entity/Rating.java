package com.anshuman.ratingservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("rating")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    private String ratingId;

    @Field("user_id")
    private String userId;

    @Field("hotel_id")
    private String hotelId;

    private float rating;
    private String feedback;

}
