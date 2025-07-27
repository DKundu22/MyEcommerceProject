package com.ecommerce.utility;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class CreditCardDataReader {

    private JSONObject jsonObject;

    public CreditCardDataReader(String filePath) {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(filePath);
            Object obj = parser.parse(reader);
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }

    public JSONObject getValidCreditCard() {
        return (JSONObject) jsonObject.get("valid");
    }

    public JSONArray getInvalidCreditCards() {
        return (JSONArray) jsonObject.get("invalid");
    }
}
