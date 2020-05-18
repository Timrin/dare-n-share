package Converter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

public class JsonConverterFriend {

    public HashMap JsonToJava(String friend) {
        HashMap addingFriend = new HashMap();
        Object obj = new JSONObject();

        try {
            obj = new JSONParser().parse(friend);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        org.json.simple.JSONObject jo = (JSONObject) obj;

        // sets
        addingFriend.put("senderID", jo.get("senderID"));
        addingFriend.put("friendEmail", jo.get("friendEmail"));

        return addingFriend;
    }
}
