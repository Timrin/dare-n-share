package Converter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Kamilla
 * @date 14/04-20
 * @version 1.0
 * This class parses a json object recieved from server to a java object
 */

public class JsonConverterScore {

    /**
     * This method parses Json objects from file to Java objects.
     */

    public Score JsonToJava(String newScore){
        Score score = new Score();
        Object obj = new JSONObject();

        try {

            obj = new JSONParser().parse(newScore);

        }catch (ParseException p){
            p.printStackTrace();
        }
        org.json.simple.JSONObject jo = (JSONObject) obj;

        score.setUid((String) jo.get("uid"));
        score.setDid((String) jo.get("dare_id"));

        //System.out.println("uid: " + score.getUid() + "\n" + "did: " + score.getDid() + "\n");

        score.setScore(((Map) jo.get("score")));

        Iterator<Map.Entry> itr1 = score.getScore().entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
        return score;
    }


}
