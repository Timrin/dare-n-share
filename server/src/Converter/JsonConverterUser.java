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
 * @version 1.0
 * This class parses json objects to java objects and the other way around.
 * @date 14/04-20
 */

public class JsonConverterUser {

    /**
     * This method converts a JSON formatted String into a User Java object
     *
     * @param newUser String user
     * @return Java User
     */
    public User JsonToJava(String newUser) {

        User user = new User();

        //Parses string into JSONObject for easier handling of String
        Object obj = new JSONObject();
        try {
            obj = new JSONParser().parse(newUser);
        } catch (ParseException p) {
            p.printStackTrace();
        }
        org.json.simple.JSONObject jo = (JSONObject) obj;

        //Sets user ID
        user.setUid((String) jo.get("uid"));
        System.out.println("JsonConverterUser:JsonToJava, new user ID set to: " + user.getUid());

        //Sets user name
        user.setName((String) jo.get("name"));
        System.out.println("JsonConverterUser:JsonToJava, new user name set to: " + user.getName());

        //Sets user email
        user.setEmail((String)jo.get("email"));
        System.out.println(user.getEmail());


        //Sets array of dares linked to user
        JSONArray dares = (JSONArray) jo.get("dares");
        user.setDares(dares);

        //Sets array of friends connected to user
        JSONArray ja = (JSONArray) jo.get("friends");
        if (ja != null) {
            ArrayList<String> friendsList = new ArrayList<>();
            Iterator iterator = ja.iterator();

            while (iterator.hasNext()) {
                Map map = (Map) iterator.next();
                friendsList.add(map.get("uid").toString());
            }
            user.setFriendsList(ja);
        }

        return user;
    }

    public String JavaToJson(User user) {

        JSONObject jo = new JSONObject();
        jo.put("uid", user.getUid());
        jo.put("name", user.getName());
        jo.put("email",user.getEmail());

        System.out.println(user.getEmail());

        // puts dare ID in an array
        ArrayList<String> dareList = user.getDares();
        JSONArray dareId = new JSONArray();

        for (int i = 0; i < dareList.size(); i++) {
            Map map = new LinkedHashMap();
            map.put("id", dareList.get(i));
            dareId.add(map);
        }
        jo.put("dares", dareId);

        // puts friends in an array
        ArrayList<Map> friendsList = user.getFriendsList();
        JSONArray friends = new JSONArray();

        for (int i = 0; i < friendsList.size(); i++) {
            //Map map = new LinkedHashMap();
            //map.put(friendsList.get(i));
            friends.add(friendsList.get(i));
        }

        /*
        * "friends": [
        * { name:
        * uid:
        * email
        * },
        * {
        * name:
        * uid:
        * email:
        * }
        *
        * */

        jo.put("friends", friends);

        System.out.println(jo.toJSONString() + " JSON CONVERTER");

        return jo.toJSONString();
    }
}

