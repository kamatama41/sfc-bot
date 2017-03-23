package com.kamatama41.sfcbot.script;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Tweet {
    // private static final String URL = "http://localhost:8080/tweet";
    private static final String URL = "https://sfcbot.herokuapp.com/tweet";
    private static final String USERNAME = System.getenv("SECURITY_USER_NAME");
    private static final String PASSWORD = System.getenv("SECURITY_USER_PASSWORD");

    public static void main(String[] args) throws Exception {
        HttpURLConnection http = (HttpURLConnection) new URL(URL).openConnection();
        http.setDoOutput(true);
        String basicAuth = USERNAME + ":" + PASSWORD;
        http.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(basicAuth.getBytes()));
        http.setRequestMethod("POST");
        http.connect();

        StringBuilder body = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()))) {
            String line;
            while((line = br.readLine()) != null) {
                body.append(line);
            }
        }
        System.out.println(http.getResponseCode() + ": " + body.toString());
    }
}
