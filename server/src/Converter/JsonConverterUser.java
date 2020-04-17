package Converter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Kamilla
 * @date 14/04-20
 * @version 1.0
 * This class parses json objects to java objects and the other way around.
 * */

public class JsonConverterUser {
    private User user;

    public String JsonToJava(String newUser) throws ParseException {
        user = new User();

        Object obj = new JSONParser().parse(newUser);
        org.json.simple.JSONObject jo = (JSONObject) obj;

        user.setUid((String) jo.get("uid"));
        user.setName((String) jo.get("name"));
        System.out.println(user.getUid());
        System.out.println(user.getName());


        //todo set friends array to user array + it doesn't work.
       /* JSONArray ja = (JSONArray) jo.get("friends");
        //Iterator iteratorFriends = ja.iterator();
        Iterator <Map> friendsArray = ja.iterator();
        while(friendsArray.hasNext()){
            //System.out.println(friendsArray.next());
            Map.Entry pair = (Map.Entry) friendsArray.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }*/
       return user.toString();
    }

    public String JavaToJson() {

        JSONObject jo = new JSONObject();
        jo.put("uid", user.getUid());
        jo.put("name", user.getName());

        // todo put friends array
        return jo.toJSONString();
    }


}

