package com.criscendo.api.algorithmia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlgorithmiaEmotionRecognizerTest {

    @Autowired
    private AlgorithmiaEmotionRecognizer recognizer;

    @Test
    public void test() throws Exception {
        assertThat(recognizer.getEmotion("https://s3-media3.fl.yelpcdn.com/photo/f9B0qMeLgIefDanaeAfn3A/60s.jpg").getLabel()).isEqualTo("Angry");
    }

}