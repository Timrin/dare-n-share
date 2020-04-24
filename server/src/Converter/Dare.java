package Converter;

import org.json.simple.JSONArray;

import java.util.Map;

/**
 * @author Kamilla
 * @date 14/04-20
 * @version 1.0
 * This is an entity class
 */

public class Dare {

    private Map objective;
    private Map scope;
    private JSONArray participants;


    public Dare() {
        participants = new JSONArray();
    }

    public JSONArray getParticipants() {
        return participants;
    }

    public void setParticipants(JSONArray newArray) {
        participants.add(newArray);
        System.out.println("SE HER KAMILLA OG JULIA "+newArray.get(1));
    }

    public Map getObjective() {
        return objective;
    }

    public void setObjective(Map objective) {
        this.objective = objective;
    }

    public Map getScope() {
        return scope;
    }

    public void setScope(Map scope) {
        this.scope = scope;
    }
}
