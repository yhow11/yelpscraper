package com.criscendo.api.algorithmia;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlgorithmiaEmotionRecognizer extends Algorithmia {

    private final static String ALGO_NAME = "deeplearning/EmotionRecognitionCNNMBP/1.0.1";
    private final static Integer DEFAULT_NUM_RESULT = 1;

    private final static String INPUT_IMAGE = "image";
    private final static String INPUT_NUM_RESULTS = "numResults";

    private final static String OUTPUT_RESULTS = "results";
    private final static String OUTPUT_EMOTIONS = "emotions";

    public AlgorithmiaEmotionRecognizer(String secret) throws Exception{
        super(ALGO_NAME, secret);
    }

    public Emotion getEmotion(String url) throws Exception{
        Map<String, Object> input = new HashMap<>();
        input.put(INPUT_IMAGE, url);
        input.put(INPUT_NUM_RESULTS, DEFAULT_NUM_RESULT);
        JsonArray results = get(input).getAsJsonArray(OUTPUT_RESULTS);
        if(results.size()  > 0){
            JsonArray emotions = results.get(0).getAsJsonObject().getAsJsonArray(OUTPUT_EMOTIONS);
            return new Gson().fromJson(emotions.get(0).getAsJsonObject().toString(), Emotion.class);
        }
        return null;
    }
}
