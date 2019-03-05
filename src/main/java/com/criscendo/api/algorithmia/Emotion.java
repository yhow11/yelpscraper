package com.criscendo.api.algorithmia;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Emotion {
    private String label;
    private Long confidence;
}
