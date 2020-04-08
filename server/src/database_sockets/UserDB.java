package database_sockets;

import java.util.ArrayList;

public class UserDB {

    // This array is used to test getting a user from server to client

    /* private static String [] USERID = {"LOL", "{\n" +
            "  \"uid\": 1,\n" +
            "  \"name\": \"Timothy\",\n" +
            "  \"profile_img\": \"/profile-picture/1\",\n" +
            "  \"dares\": [\n" +
            "    {\n" +
            "      \"id\": \"d1\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"d2\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"d3\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"friends\": [\n" +
            "    {\n" +
            "      \"uid\": 2,\n" +
            "      \"name\": \"Steven\",\n" +
            "      \"profile_img\": \"/profile-picture/1\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"uid\": 3,\n" +
            "      \"name\": \"Tor\",\n" +
            "      \"profile_img\": \"/profile-picture/1\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"};*/

    // this arraylist is used to test recieving a user from client.
    private static ArrayList<String> ID = new ArrayList<>();

    public UserDB(){

    }

    /**
     * Expects a string of user from client/ android app
     * adds user to arrayslist of users.
     * */
    public void addUser(String user){
        ID.add(user);
    }
    /**
     * Expects an ID from the android app.
     * returns the user with that ID.
     * */
    public String getUser(int id){
        return ID.get(id);
    }

}
