package Converter;

import org.json.simple.JSONArray;

/**
 * @author Kamilla
 * @version 1.0
 * This class is an entity class
 * @date 14/04-20
 */

public class User {

    private String uid;
    private String name;
    private JSONArray dares;
    private JSONArray friends;

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONArray getDares() {
        return dares;
    }

    public void setDares(JSONArray dare) {
        //dares.add(dare);
        this.dares = dare;
    }

    public JSONArray getFriends() {
        return friends;
    }

    public void setFriends(JSONArray friend) {
        // friends.add(friend);
        this.friends = friend;
        // System.out.println("1 friend "+friends.get(0));
        // System.out.println("HIIIIIIIII "+ friends.get(1));
    }
}
