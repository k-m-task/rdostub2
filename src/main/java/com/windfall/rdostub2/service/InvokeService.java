package com.windfall.rdostub2.service;

import com.google.gson.*;
import com.windfall.rdostub2.common.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvokeService {

    @Autowired
    private RdoService rdoService;

    private static final String METHOD = "method";
    private static final String PARAMS = "params";
    private static final String RESULT = "result";
    private static final String ERROR = "error";
    static final String CODE = "code";
    static final String MESSAGE = "message";
    static final String DATA = "data";

    public Object invoke(String requestObject) {
        /*
         * METHODS generateIntegerSequences(String apiKey,int n,int
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
         * on the server while parsing the JSON text.
         * -32600 Invalid Request The JSON sent is not a valid Request object.
         * -32601 Method not found The method does not exist / is not available.
         * -32602 Invalid params Invalid method
         * parameter(s). -32603 Internal error Internal JSON-RPC error.
         *
         */
        JsonObject requestJson;
        JsonObject errorObject = null;
        String method = null;
        JsonObject params = null;
        String id = "";
        JsonObject responseJson = new JsonObject();
        try {
            // lint the incoming Json for validity
            requestJson = JsonParser.parseString(requestObject).getAsJsonObject();
            method = requestJson.get(METHOD).getAsString();
            params = requestJson.get(PARAMS).getAsJsonObject();
            id = requestJson.get("id").getAsString();
        } catch (JsonSyntaxException e) {
            errorObject = getErrorObject(-32700);
        } catch (NullPointerException e) {
            if (method == null) {
                errorObject = getErrorObject(-32600);
            } else if (params == null) {
                // params = null
                errorObject = getErrorObject(-32602);
            } else {
                throw new IdNotFoundException();
            }
        }
        responseJson.addProperty("jsonrpc", "2.0");
        if (errorObject == null) {
            // NOTE: the method property is only added to the response for signed requests.
            // responseJson.addProperty(METHOD, method);
            if (method.equals("generateIntegers")) {
                responseJson.add(RESULT, rdoService.generateIntegers(params));
            }
        } else {
            responseJson.add(ERROR, errorObject);
        }
        responseJson.addProperty("id", id);
        return responseJson.toString();
    }

    private JsonObject getErrorObject(Integer errorCode) {
        JsonObject errorObj = new JsonObject();
        errorObj.addProperty(CODE, errorCode);
        if (errorCode == -32700) {
            errorObj.addProperty(MESSAGE, "Parse error");
        }
        if (errorCode == -32600) {
            errorObj.addProperty(MESSAGE, "Invalid Request");
        }
        if (errorCode == -32601) {
            errorObj.addProperty(MESSAGE, "Method not found");
        }
        if (errorCode == -32602) {
            errorObj.addProperty(MESSAGE, "Invalid params");
        }
        if (errorCode == -32603) {
            errorObj.addProperty(MESSAGE, "Internal error");
        }
        errorObj.add(DATA, JsonNull.INSTANCE);
        return errorObj;
    }
}
