package com.criscendo.api.yelp.api;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class YelpAPITest {

    @Test
    public void test() throws Exception {
        YelpAPI api = new YelpAPI("wildflour café bakery", "makati");
        assertThat(api.getAddress()).isEqualTo("125 LP Leviste Street, Salcedo Village G/F Makati, 1227 Metro Manila Philippines");
        assertThat(api.getPrice()).isEqualTo("₱301-500");
        assertThat(api.getRate()).isEqualTo("4.5 star rating");
        assertThat(api.getStatus()).isEqualTo("Open nowOpen now");
        assertThat(api.getSchedule()).isEqualTo("7:00 am-10:00 pm");
        assertThat(api.getContact()).isEqualTo("+63-2-8087072");
        assertThat(api.getReviews().size()).isEqualTo(67);
    }

}
