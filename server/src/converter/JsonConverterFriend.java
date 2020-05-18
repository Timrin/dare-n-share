package converter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.HashMap;

/**
 * @author Kamilla, Steven, Julia
 * @date 18.05.20
 * @version
 * This class hold a method that converts json objects  to java objects
 */

public class JsonConverterFriend {
    /**
     * This method recieves a string, and converts it from json syntax to java objects
     * @param friend
     * @return
     */
    public HashMap JsonToJava(String friend) {
        HashMap addingFriend = new HashMap();
        Object obj = new JSONObject();

        try {
            obj = new JSONParser().parse(friend);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        org.json.simple.JSONObject jo = (JSONObject) obj;

        addingFriend.put("senderID", jo.get("senderID"));
        addingFriend.put("friendEmail", jo.get("friendEmail"));

        return addingFriend;
    }
}
