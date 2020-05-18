package Converter;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Kamilla
 * @version 1.0
 * This class is an entity class
 * @date 14/04-20
 */

public class User {

    private String uid;
    private String name;
    private ArrayList<String> dares;
    private ArrayList<Map> friendsList;
    private String eMail;

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

    public ArrayList<String> getDares() {
        return dares;
    }

    public void setDares(ArrayList dare) {
        this.dares = dare;
    }


    public ArrayList<Map> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(ArrayList<Map> friend) {
        this.friendsList = friend;

    }

    public String getEmail() {
        return eMail;
    }

    public void setEmail(String eMail) {
        this.eMail = eMail;
    }

    /* public boolean addFriend(String userID){
        if (friendsList.contains(userID)){
            return false;
        }
        else{
            friendsList.add(userID);
            return true;
        }
    }*/
}
