package com.criscendo.api.algorithmia;

import com.algorithmia.AlgorithmiaClient;
import com.algorithmia.algo.AlgoResponse;
import com.algorithmia.algo.Algorithm;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public abstract class Algorithmia {

    protected Algorithm algo;

    public Algorithmia(String algoName, String secret) throws Exception {
        AlgorithmiaClient client = com.algorithmia.Algorithmia.client(secret);
        this.algo = client.algo(algoName);
        algo.setTimeout(300L, java.util.concurrent.TimeUnit.SECONDS);
    }

    public JsonObject get( Map<String, Object> input ) throws Exception{
        AlgoResponse result = algo.pipeJson(new Gson().toJson(input));
        return new Gson().fromJson(result.asJsonString(), JsonObject.class);
    }
}
