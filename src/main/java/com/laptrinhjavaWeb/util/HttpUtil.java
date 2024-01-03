package com.laptrinhjavaWeb.util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtil {
    private String value;
    public HttpUtil(String value){
        this.value=value;
    }
    public <T>T toModel(Class<T>tClass){
        try{
            return new ObjectMapper().readValue(this.value,tClass);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed change Json");
            return null;
        }
    }
    public static HttpUtil of(BufferedReader reader){//json
        StringBuilder sb=new StringBuilder();
        String line;
        try{
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            System.out.println(sb);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return new HttpUtil(sb.toString());
    }
}