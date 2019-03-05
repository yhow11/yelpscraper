package com.criscendo.api.yelp;

import java.util.List;

public interface YelpService {

    public YelpProfilePage getPage(String name, String city) throws  Exception;
}
