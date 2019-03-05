package com.criscendo.api.yelp;

import com.criscendo.api.yelp.api.Review;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class YelpProfilePage {
    private String name;
    private String rate;
    private String address;
    private String contact;
    private String schedule;
    private String status;
    private String price;
    private List<YelpProfilePageReview> reviews;
}
