package com.switchfully.api.security.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.switchfully.domain.exceptions.EmailNotValidException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExternalEmailValidation {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final String GET_URL = "https://api.emailverifyapi.com/v3/lookups/json?key=C387D7D88D1899BF&email=";
    private static boolean validFormat;

    static void sendGET(String email) throws IOException {
        URL obj = new URL(GET_URL + email);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String json = response.toString();
            JsonNode jsonNode = objectMapper.readTree(json);
            validFormat = jsonNode.get("validFormat").asBoolean();
        }
        //if (!validFormat) throw new EmailNotValidException(email);

    }
}
