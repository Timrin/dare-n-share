package Converter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Kamilla
 * @date 14/04-20
 * @version 1.0
 * This class parses json objects to java objects and the other way around.
 * */

public class JsonConverterUser {

    /**
     * This method converts a JSON formatted String into a User Java object
     * @param newUser String user
     * @return Java User
     */
    public User JsonToJava(String newUser) {

        User user = new User();

        //Parses string into JSONObject for easier handling of String
        Object obj = new JSONObject();
        try {
            obj = new JSONParser().parse(newUser);
        }catch (ParseException p){
            p.printStackTrace();
        }
        org.json.simple.JSONObject jo = (JSONObject) obj;

        //Sets user ID
        user.setUid((String) jo.get("uid"));
        System.out.println("JsonConverterUser:JsonToJava, new user ID set to: " + user.getUid());

        //Sets user name
        user.setName((String) jo.get("name"));
        System.out.println("JsonConverterUser:JsonToJava, new user name set to: " + user.getName());

        //Sets array of dares linked to user
        JSONArray dares = (JSONArray)jo.get("dares");
        user.setDares(dares);

        //Sets array of friends connected to user
        JSONArray JArray = (JSONArray)jo.get("friends");
        ArrayList<String> friendsList = new ArrayList<>();
        Iterator iterator = JArray.iterator();

        while(iterator.hasNext()){
            Map map = (Map) iterator.next();
            friendsList.add(map.get("uid").toString());
        }
        user.setFriendsList(JArray);

        return user;
    }

    public String JavaToJson(User user) {

        JSONObject jo = new JSONObject();
        jo.put("uid", user.getUid());
        jo.put("name", user.getName());

        JSONArray dares = user.getDares();
        jo.put("dares",dares);

        ArrayList<String> friendsList = user.getFriendsList();
        JSONArray friends = new JSONArray();

        for(int i = 0; i < friendsList.size(); i++){
            Map map = new LinkedHashMap();
            map.put("uid", friendsList.get(i));
            friends.add(map);
        }

        jo.put("friends",friends);

        // todo put friends array
        return jo.toJSONString();
    }
}

