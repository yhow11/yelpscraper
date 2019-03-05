package com.criscendo.api.yelp.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class YelpAPI {

    private final String BASE_URL = "https://www.yelp.com/biz/" ;


    private final String TITLE_ATT = "title" ;
    private final String SOURCE_ATT = "src";


    private final String RATE_SELECTOR = ".rating-very-large" ;
    private final String ADDRESS_SELECTOR = ".map-box-address address" ;
    private final String CONTACT_SELECTOR = ".biz-phone" ;
    private final String SCHEDULE_SELECTOR = ".u-space-r-half span" ;
    private final String STATUS_SELECTOR = ".u-space-r-half span" ;
    private final String PRICE_SELECTOR = ".price-description" ;

    private final String REVIEW_SELECTOR = ".review-list .reviews li:not(:first-child) .review.review--with-sidebar";
    private final String REVIEW_SIDEBAR_SELECTOR = ".review-sidebar-content";
    private final String REVIEW_CONTENT_SELECTOR = ".review-content";

    private final String REVIEW_USERNAME_SELECTOR = REVIEW_SIDEBAR_SELECTOR+" .user-display-name";
    private final String REVIEW_AVATAR_SELECTOR = REVIEW_SIDEBAR_SELECTOR+" .photo-box-img";
    private final String REVIEW_USER_LOCATION_SELECTOR = REVIEW_SIDEBAR_SELECTOR+" .user-location";
    private final String REVIEW_FRIEND_COUNT_SELECTOR = REVIEW_SIDEBAR_SELECTOR+" .friend-count b";
    private final String REVIEW_COUNT_SELECTOR = REVIEW_SIDEBAR_SELECTOR+" .review-count b";
    private final String REVIEW_PHOTO_COUNT_SELECTOR = REVIEW_SIDEBAR_SELECTOR+" .photo-count b";
    private final String REVIEW_COMMENT_SELECTOR = REVIEW_CONTENT_SELECTOR+" p";
    private final String REVIEW_RATE_SELECTOR = REVIEW_CONTENT_SELECTOR+" .rating-large";
    private final String REVIEW_DATE_SELECTOR = REVIEW_CONTENT_SELECTOR+" .rating-qualifier";

    private Document document;
    private String name;
    private String city;

    public YelpAPI(String name, String city) throws Exception {
        if(name == null || city == null) throw new Exception("Name and City are required.");
        this.name = name;
        this.city = city;
        this.document = connect(name, city, null);
    }

    private Document connect(String name, String city, Integer start)  throws Exception{
        name = name.toLowerCase().replaceAll("\\s+", "-");
        city = city.toLowerCase().replaceAll("\\s+", "-");
        String url = BASE_URL+name+"-"+city;
        if(start != null){
            url += "?start="+start;
        }
        return Jsoup.connect(url).get();
    }

    public String getRate(){
       return document.select(RATE_SELECTOR).attr(TITLE_ATT);
    }

    public String getAddress(){
        return document.select(ADDRESS_SELECTOR).text().trim();
    }

    public String getContact(){
        return document.select(CONTACT_SELECTOR).text().trim();
    }

    public String getSchedule(){
        return document.select(SCHEDULE_SELECTOR).stream().map(element->element.text().trim()).reduce((item1, item2)-> item1+"-"+item2).get();
    }

    public String getStatus(){
        return document.select(STATUS_SELECTOR).stream().map(element->element.parent().nextElementSibling().text()).collect(Collectors.joining());
    }
    public String getPrice(){
        return document.select(PRICE_SELECTOR).text().trim();
    }

    private Boolean hasReviews(){
        return document.select(REVIEW_SELECTOR).size() > 0;
    }
    public List<Review> getReviews(){
        List<Review> totalReviews = new ArrayList<>();
        Integer start = null;
        while(hasReviews()){
            try{
                document = connect(this.name, this.city, start);
                List<Element> reviewElements =  document.select(REVIEW_SELECTOR).stream().map(element -> element).collect(Collectors.toList());
                List<Review> reviews = reviewElements.stream().map(element ->{
                    String username = element.select(REVIEW_USERNAME_SELECTOR).text().trim();
                    String avatar = element.select(REVIEW_AVATAR_SELECTOR).attr(SOURCE_ATT);
                    String location = element.select(REVIEW_USER_LOCATION_SELECTOR).text().trim();
                    String friendCount = element.select(REVIEW_FRIEND_COUNT_SELECTOR).text().trim();
                    String reviewCount = element.select(REVIEW_COUNT_SELECTOR).text().trim();
                    String photoCount = element.select(REVIEW_PHOTO_COUNT_SELECTOR).text().trim();
                    String reviewComment = element.select(REVIEW_COMMENT_SELECTOR).text().trim();
                    String rate = element.select(REVIEW_RATE_SELECTOR).attr(TITLE_ATT);
                    String date = element.select(REVIEW_DATE_SELECTOR).text();

                    return Review.builder()
                            .username(username)
                            .avatar(avatar)
                            .location(location)
                            .friendCount(friendCount)
                            .reviewCount(reviewCount)
                            .photoCount(photoCount)
                            .reviewComment(reviewComment)
                            .rate(rate)
                            .date(date)
                            .build();
                }).collect(Collectors.toList());
                totalReviews.addAll(reviews);
            } catch (Exception e){
                e.printStackTrace();
            }

            start = start == null? 20: start+20;
        }
      return totalReviews;
    }
}
