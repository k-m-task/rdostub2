package com.windfall.rdostub2.rdorequestmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.google.gson.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RdoRequestModel {
    String jsonrpc;
    String method;
    JsonObject params;
    JsonObject id;
}
