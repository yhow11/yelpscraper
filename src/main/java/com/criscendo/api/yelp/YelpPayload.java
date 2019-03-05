package com.criscendo.api.yelp;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class YelpPayload {
    @NotNull
    private String name;
    @NotNull
    private String city;
}
