package Converter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Kamilla
 * @version 1.0
 * This class parses Json objects into Java Objects
 * @date 14/04-20
 */
public class JsonConverterDare {
    private Dare dare;

    /**
     * This method parses a String in JSON body, recieved from server, to a Dare Java object.
     */
    public void JsonToJava(String newdare) throws ParseException {
        this.dare = new Dare();

        Object obj = new JSONParser().parse(newdare);
        org.json.simple.JSONObject jo = (JSONObject) obj;

        this.dare.setObjective((Map) jo.get("objective"));

        Iterator<Map.Entry> iteratorObjective = this.dare.getObjective().entrySet().iterator();
        while (iteratorObjective.hasNext()) {
            Map.Entry pair = iteratorObjective.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }

        this.dare.setScope((Map) jo.get("scope"));
        Iterator<Map.Entry> iteratorScope = this.dare.getScope().entrySet().iterator();
        while (iteratorScope.hasNext()) {
            Map.Entry pair = iteratorScope.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
        /*
         * todo This array is not saved in Dare entity class.
         * */
        JSONArray ja = (JSONArray) jo.get("participants");
        Iterator iteratorParticipant = ja.iterator();

        while (iteratorParticipant.hasNext()) {

            iteratorObjective = ((Map) iteratorParticipant.next()).entrySet().iterator();

            while (iteratorObjective.hasNext()) {
                Map.Entry pair = iteratorObjective.next();
                System.out.println(pair.getKey() + " : " + pair.getValue());

                // dare.setParticipants((ArrayList<String>) pair.getValue());
            }
        }
    }

    /**
     * This method returns a JSONString after converting Java objects into s JSON string.
     * This method is supposed to be called upon in the dareEndpoint through the controller
     */
    public String JavaToJson() {

        JSONObject jo = new JSONObject();

        Map m = new LinkedHashMap();
        m.putAll(dare.getObjective());
        jo.put("objective", m);

        m = new LinkedHashMap();
        m.putAll(dare.getScope());
        jo.put("scope", m);

        // TODO put participant array in.
        // JSONArray ja = new JSONArray();
        // m = new LinkedHashMap();
        // m.putAll((Map) dare.getParticipants()); // ikke sikkert dareparticipants kan være arraylist..
        // ja.add(m);
        // jo.put("participants",ja);


        return jo.toJSONString();
    }

}
