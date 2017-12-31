package com.example.credr.snapsearch.utils

import com.google.gson.JsonObject

/**
 * Created by punitdama on 31/12/17.
 */
fun JsonObject.generatePostParamsForAutoSuggestion(keyword : String) : String{
    addProperty("apikey", "snapdeal")
    addProperty("requestProtocol", "PROTOCOL_JSON")
    addProperty("responseProtocol", "PROTOCOL_JSON")
    addProperty("searchString", keyword)
    return this.toString()
}