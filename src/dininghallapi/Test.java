package com.dining.dininghallapi;

import org.json.simple.JSONObject;
public class Test {
    public static void main(String [] args){

        JSONObject json = new JSONObject();
        json.put("peanuts", true);
        json.put("salt", true);
        json.put("vegan", false);
        json.put("vegetarian", false);

        System.out.println(json.toJSONString());
    }
}