/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdgb.javascripts.base64demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class FormData {
     
    public static void main(String[] args){
        try {
            prueba();
        } catch (URISyntaxException ex) {
            Logger.getLogger(FormData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void prueba() throws URISyntaxException{
        String route = "/api/Tracker/ConnectorDisconnect/";
        String url = route;
        int projectId = 1;
        Map<Object, Object> body = new HashMap<>();
        body.put("projectId", projectId);
        HttpRequest request = setPOSTRequest(url, body);
        HttpClient client = HttpClient.newHttpClient();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static HttpRequest setPOSTRequest(String url, Map<Object, Object>  body) throws URISyntaxException{
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(url))
            .headers("Content-Type", "application/json;charset=utf-8")
            .POST(buildFormDataFromMap(body)) //POST
            .build();
        return request;
    }
    
    private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        //System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
}
