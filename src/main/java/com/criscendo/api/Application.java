package com.criscendo.api;

import com.criscendo.api.algorithmia.AlgorithmiaEmotionRecognizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.criscendo.api"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public AlgorithmiaEmotionRecognizer algorithmiaEmotionRecognizer(@Value("${algorithmia.secret}") String secret) throws Exception {
        return new AlgorithmiaEmotionRecognizer(secret);
    }
}
