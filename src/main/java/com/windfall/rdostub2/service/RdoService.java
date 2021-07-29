package com.windfall.rdostub2.service;

// import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RdoService {
    // private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public JsonObject generateIntegers(JsonObject params) {
        // generateIntegers(String apiKey,int n,int min,int max,boolean replacement,int base);
        Gson gson = new Gson();

        int n = params.get("n").getAsInt();
        int min = params.get("min").getAsInt();
        int max = params.get("max").getAsInt();
        // boolean replacement
        // int base
        List<Long> randResult = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            randResult.add((long) (Math.floor(Math.random()*(max - min + 1)) + min));
        }
        /*
        "result": {
            "random": {
                "data": [1,0,1,0,1,0,1,0],
                "completionTime": "2021-07-28 02:41:53Z"
            },
            "bitsUsed": 8,
            "bitsLeft": 249992,
            "requestsLeft": 999,
            "advisoryDelay": 2300
        }
        */
        JsonObject dataObject = new JsonObject();
        JsonArray randResultObject = gson.toJsonTree(randResult).getAsJsonArray();
        dataObject.add("data", randResultObject);
        JsonObject resultObject = new JsonObject();
        resultObject.add("random", dataObject);
        return resultObject;
        // return params;
    }
}
