package Server;

import dareSetUp.Dare;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This Class facilitates easier input methods into an ArrayList within a HashMap
 */
public class UserDareMap {
    private HashMap<User, ArrayList<Dare>> map;

    public UserDareMap(){
        map = new HashMap<>();
    }

    public void put(User user){
        if(!map.containsKey(user)){
            map.put(user, new ArrayList<Dare>());
        }
    }

    /**
     * If the User does not exist in the HashMap, it puts it in with a new ArrayList, and adds the dare
     * If the User already exists, it only adds the dare to the corresponding ArrayList.
     * @param user A User, new or existing in the HashMap
     * @param dare A Dare connected to the User
     */
    public void put(User user, Dare dare){
        if(!map.containsKey(user)){
            map.put(user, new ArrayList<Dare>());
            map.get(user).add(dare);
        }
        if(map.containsKey(user)) {
            map.get(user).add(dare);
        }
    }
}
