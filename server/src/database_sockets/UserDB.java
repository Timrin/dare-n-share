package database_sockets;
import java.util.HashMap;
/**
 * @author Julia and Kamilla
 *
 * This class is only for testing purposes. It generates an id for every new User
 * */
public class UserDB {

    private HashMap<Integer,String> users = new HashMap<>();
    private int nextId = 0;


    public UserDB(){

    }

    /**
     * Expects a string of user from client/ android app
     * adds user to arrayslist of users.
     * */
    public int addUser(String user){
        users.put(nextId, user);
        return nextId++;
    }
    /**
     * Expects an ID from the android app.
     * returns the user with that ID.
     * */
    public String getUser(int id){
        return users.get(id);
    }

}
