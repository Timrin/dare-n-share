package Converter;

import org.json.simple.JSONArray;

import java.util.ArrayList;

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
    private ArrayList<String> friendsList;

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

    public ArrayList<String> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(ArrayList<String> friend) {
        // friends.add(friend);
        this.friendsList = friend;
        // System.out.println("1 friend "+friends.get(0));
        // System.out.println("HIIIIIIIII "+ friends.get(1));
    }

    public boolean addFriend(String userID){
        if (friendsList.contains(userID)){
            return false;
        }
        else{
            friendsList.add(userID);
            return true;
        }
    }
}
