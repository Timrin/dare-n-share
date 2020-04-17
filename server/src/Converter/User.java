package Converter;
/**
 * @author Kamilla
 * @date 14/04-20
 * @version 1.0
 * This class is an entity class
 * */

public class User {

    private String uid;
    private String name;
    private String[] friends; //?? todo Find a way to save array from json object in a java array

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

    public String[] getFriends() {
        return friends;
    }

    public void setFriends(String[] friends) {
        this.friends = friends;
    }
}
