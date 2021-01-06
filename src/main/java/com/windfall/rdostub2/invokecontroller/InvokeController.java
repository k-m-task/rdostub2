package com.windfall.rdostub2.invokecontroller;

import com.google.gson.*;
import com.windfall.rdostub2.exception.IdNotFoundException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvokeController {

    static final String CODE = "code";
    static final String MESSAGE = "message";
    static final String DATA = "data";

    @PostMapping(path = "/invoke", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
    public Object invoke(@RequestBody String jsonString) {
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
         * jsonrpc=string,method=string,params=object,id=string Code Message Meaning
         * 
         * -32700 Parse error Invalid JSON was received by the server. An error occurred
         * on the server while parsing the JSON text. -32600 Invalid Request The JSON
         * sent is not a valid Request object. -32601 Method not found The method does
         * not exist / is not available. -32602 Invalid params Invalid method
         * parameter(s). -32603 Internal error Internal JSON-RPC error.
         */

        /*
         * TODO: modify project to return POJO instead of string, so we can attach error
         * codes string
         */
        JsonObject requestJson = null;
        JsonObject errorObject = null;
        JsonObject responseJson = new JsonObject();
        String method = null;
        JsonObject params = null;
        try {
            // lint the incoming Json as valid
            requestJson = JsonParser.parseString(jsonString).getAsJsonObject();
            method = requestJson.get("method").getAsString();
        } catch (JsonSyntaxException e) {
            // this catch block is not used even when string is invalid
            errorObject = new JsonObject();
            errorObject.addProperty(CODE, -32700);
            errorObject.addProperty(MESSAGE, "Parse error");
            errorObject.add(DATA, JsonNull.INSTANCE);
        } catch (NullPointerException e) {
            errorObject = new JsonObject();
            errorObject.addProperty(CODE, -32600);
            errorObject.addProperty(MESSAGE, "Invalid Request");
            errorObject.add(DATA, JsonNull.INSTANCE);
        }
        params = requestJson.getAsJsonObject("params");
        if (params == null) {
            errorObject = new JsonObject();
            errorObject.addProperty(CODE, -32602);
            errorObject.addProperty(MESSAGE, "Invalid params");
            errorObject.add(DATA, JsonNull.INSTANCE);
        }
        /**/
        String id = "";
        try {
            id = requestJson.get("id").getAsString();
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        responseJson.addProperty("jsonrpc", "2.0");
        if (errorObject != null) {
            responseJson.add("error", errorObject);
        } else {
            responseJson.addProperty("method", method);
            if (method.equals("generateIntegers")) {
                responseJson.add("result", params);
            }
        }
        responseJson.addProperty("id", id);
        return responseJson.toString();
    }
}
