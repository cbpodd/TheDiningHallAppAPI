package com.dining.dininghallapi;

import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class APIService {
    // @RequestMapping(value="/dininghall", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    // public String getDiningHall() {
    //     String pattern = "[ { \"name\":%s, \"mealtimes\":[{\"name\":%s,\"kitchens\":[{  \"name\":%s, \"dishes\":[{  \"name\":%s, \"allergies\":[%b,%b,%b,%b,%b]}] }]}]}]";
    //     return String.format(pattern, "Everybody's Kitchen", "breakfast", "Mezze Bar", "TestDish", true, true, false, false, false);
    // }


    @RequestMapping(value="/dine", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getDine() {
        LocalDate d = LocalDate.now();
        DiningHall dh = new DiningHall("Everybody's Kitchen", d);
        return dh.toJSON();
    }

    public static final String[] HALLS =  {"Everybody's Kitchen", "USC Village Dining Hall", "Parkside Restaurant & Grill"};
    public static final String[] halls = {"everybody", "village", "parkside"};

    @RequestMapping(value="/alldininghalls", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public JSONArray getDiningHalls() {
        LocalDate d = LocalDate.now();
        JSONArray json = new JSONArray();
        for(String s : HALLS){
            json.add(new DiningHall(s, d).toSimplifiedJSON());
        }
        // json.add(new DiningHall(EVERYBODY , d).toJSON());
        // json.add(new DiningHall(VILLAGE , d).toJSON());
        // json.add(new DiningHall(PARKSIDE , d).toJSON());
        return json;
    }

    @RequestMapping(value="/dininghall", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getDiningHall(String dininghall){
        LocalDate d = LocalDate.now();
        DiningHall dh = new DiningHall(getHall(dininghall), d);
        if(dh == null){
            return new JSONObject();
        }
        return dh.toJSON();
    }

    @RequestMapping(value="/pulldate", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getDiningHallDate(String name, int year, int month, int day ){
        LocalDate d = LocalDate.of(year,month, day);
        String dininghall = getHall(name);
        DiningHall dh = new DiningHall(dininghall, d);
        if(dh == null){
            return new JSONObject();
        }
        return dh.toJSON();
    }


    @RequestMapping(value="/pulldatealldininghalls", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public JSONArray getAllDiningHallsDate(int year, int month, int day) {
        LocalDate d = LocalDate.of(year, month, day);
        JSONArray json = new JSONArray();
        for(String s : HALLS){
            json.add(new DiningHall(s, d).toJSON());
        }
        return json;
    }

    private String getHall(String name){
        for(int i = 0; i < halls.length ; i++){
            if(name.equals(halls[i])) return HALLS[i];
        }
        return "";
    }


    /*
Dairy
Eggs
Fish
Food Not Analyzed for Allergens
Peanuts
Pork
Sesame
Shellfish
Soy
Tree Nuts
Vegan
Vegetarian
Wheat / Gluten
    */
}
