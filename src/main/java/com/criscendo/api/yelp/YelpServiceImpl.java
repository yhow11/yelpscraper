package com.criscendo.api.yelp;

import com.criscendo.api.algorithmia.AlgorithmiaEmotionRecognizer;
import com.criscendo.api.algorithmia.Emotion;
import com.criscendo.api.yelp.api.YelpAPI;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class YelpServiceImpl implements YelpService {

    @Autowired
    private AlgorithmiaEmotionRecognizer recognizer;

    @Override
    public YelpProfilePage getPage(String name, String city) throws  Exception {
        YelpAPI api = new YelpAPI(name, city);

        List<YelpProfilePageReview> reviews = api.getReviews().stream().map(review->{
            try{
                Emotion emotion = recognizer.getEmotion(review.getAvatar());

                return YelpProfilePageReview.builder()
                        .username(review.getUsername())
                        .imgURL(review.getAvatar())
                        .location(review.getLocation())
                        .friendCount(review.getFriendCount())
                        .reviewCount(review.getReviewCount())
                        .photoCount(review.getPhotoCount())
                        .reviewComment(review.getReviewComment())
                        .rate(review.getRate())
                        .date(review.getDate())
                        .emotion(emotion)
                        .build();
            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }).collect(Collectors.toList());


        return YelpProfilePage.builder()
                .name(name)
                .address(api.getAddress())
                .rate(api.getRate())
                .schedule(api.getSchedule())
                .status(api.getStatus())
                .price(api.getPrice())
                .reviews(reviews).build();
    }
}
