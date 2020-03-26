package Server;

import dareSetUp.Dare;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

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

    public void put(User user, Dare dare){
        if(!map.containsKey(user)){
            map.put(user, new ArrayList<Dare>());
        }
        if(map.containsKey(user)) {
            map.get(user).add(dare);
        }
    }


}
