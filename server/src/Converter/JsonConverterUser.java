package Converter;


import api_endpoints.UserEndpoint;
import database.DBController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.SQLException;

/**
 * @author Kamilla
 * @date 14/04-20
 * @version 1.0
 * This class parses json objects to java objects and the other way around.
 * */

public class JsonConverterUser {

    private Controller controller;

    public JsonConverterUser(){
        this.controller = new Controller();
    }

    public User JsonToJava(String newUser) throws ParseException {
        User user = new User();

        Object obj = new JSONParser().parse(newUser);
        org.json.simple.JSONObject jo = (JSONObject) obj;

        user.setUid((String) jo.get("uid"));
        user.setName((String) jo.get("name"));
        System.out.println(user.getUid());
        System.out.println(user.getName());

        JSONArray dares = (JSONArray)jo.get("dares");
        user.setDares(dares);

        JSONArray friends = (JSONArray)jo.get("friends");
        user.setFriends(friends);

        return user;
    }

    public String JavaToJson(User user) {

        JSONObject jo = new JSONObject();
        jo.put("uid", user.getUid());
        jo.put("name", user.getName());

        JSONArray dares = user.getDares();
        jo.put("dares",dares);

        JSONArray friends = user.getFriends();
        jo.put("friends",friends);

        // todo put friends array
        return jo.toJSONString();
    }

    public String getUserFromDB(String uid){
        JSONObject jo = new JSONObject();
        jo.put("name",controller.getUserFromDB(uid));
        jo.put("uid", uid);

        System.out.println(jo.toJSONString());
        return jo.toJSONString();
    }




}

