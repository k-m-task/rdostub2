package com.windfall.rdostub2.invokecontroller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.google.gson.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/invoke")
public class InvokeController {

    @RequestMapping(method = POST, consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
    public String invoke(@RequestBody String jsonString) {
        /*
         * METHODS generateIntegers(String apiKey,int n,int min,int max,boolean
         * replacement,int base) generateIntegerSequences(String apiKey,int n,int
         * length,int min,int max,boolean replacement,int base)
         * generateDecimalFractions(String apiKey,int n,int decimalPlaces,boolean
         * replacement) generateGaussians(String apiKey,int n,float mean,float
         * standardDeviation,int significantDigits) generateStrings(String apiKey,int
         * n,int length,String characters,boolean replacement) generateUUIDs(String
         * apiKey,int n) generateBlobs(String apiKey,int n,int size,String format)
         * getUsage(String apiKey) generateSignedIntegers(String apiKey,int n,int
         * min,int max,boolean replacement,base,String userData)
         * generateSignedIntegerSequences(String apiKey,int n,int length,int min,int
         * max,boolean replacement,int base,String userData)
         * generateSignedDecimalFractions(String apiKey,int n,int decimalPlaces,boolean
         * replacement,String userData) generateSignedGaussians(String apiKey,int
         * n,float mean,float standardDeviation,significantDigits,String userData)
         * generateSignedStrings(String apiKey,int n,int length,String
         * characters,boolean replacement,String userData) generateSignedUUIDs(String
         * apiKey,int n,String userData) generateSignedBlobs(String apiKey,int n,int
         * size,String format,String userData) getResult(String apiKey,int serialNumber)
         * verifySignature(String random,String signature)
         * jsonrpc=string,method=string,params=object,id=string
         */
        JsonObject rootObject = JsonParser.parseString(jsonString).getAsJsonObject();
        
        //String jsonrpc = rootObject.get("jsonrpc").getAsString();
        String method = rootObject.get("method").getAsString();
        //JsonObject id = rootObject.get("method").getAsJsonObject();
        
        switch (method) {
            case "generateIntegers": {
                JsonObject paramsObject = rootObject.getAsJsonObject("params");
                return paramsObject.toString();
            }
            default: {
                return "Invalid Request";
            }
        }
    }
}
