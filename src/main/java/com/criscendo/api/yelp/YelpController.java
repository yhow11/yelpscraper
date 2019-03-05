package com.criscendo.api.yelp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class YelpController {

    @Autowired
    private YelpService yelpService;

    @PostMapping( path ="/yelp/getReviews", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<YelpProfilePageReview>> getReviews(@RequestBody YelpPayload payload) throws Exception {
        return ResponseEntity.ok(yelpService.getPage(payload.getName(), payload.getCity()).getReviews());
    }
}