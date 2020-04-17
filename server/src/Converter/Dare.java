package Converter;

import java.util.ArrayList;
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
    private ArrayList<String> participants; //?? todo Find a way to save array from json object in a java array

    public Dare() {
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

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }
}
