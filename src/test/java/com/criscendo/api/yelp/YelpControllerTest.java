package com.criscendo.api.yelp;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class YelpControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getReviews() throws Exception {
        Gson gson = new Gson();
        mvc.perform(MockMvcRequestBuilders
                .post("/yelp/getReviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(YelpPayload.builder().name("burger company").city("quezon city").build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(9)));
    }
}
