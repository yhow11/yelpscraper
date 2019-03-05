package com.criscendo.api.yelp;

import com.criscendo.api.algorithmia.Emotion;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Builder
@Data
public class YelpProfilePageReview {
    private String username;
    private String imgURL;
    private String location;
    private String friendCount;
    private String reviewCount;
    private String photoCount;
    private String reviewComment;
    private String rate;
    private String date;
    private List<YelpProfilePageReview> reviews;
    private Emotion emotion;
}
