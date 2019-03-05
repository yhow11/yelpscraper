package com.criscendo.api.yelp.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Review {
    private String username;
    private String avatar;
    private String location;
    private String friendCount;
    private String reviewCount;
    private String photoCount;
    private String reviewComment;
    private String rate;
    private String date;
}
