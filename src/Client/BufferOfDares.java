package Client;

import dareSetUp.Dare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * place and get challenges in a hashmap, it will both contain a User as well as an Arraylist of Dares. Will describe dependencies later on.
 * @param <T>
 */
public class BufferOfDares <T> {
   private HashMap <T, ArrayList<Dare>> buffer = new HashMap<>();

   public synchronized void add(T key, Dare myDare) {
    //Todo se över detta kanske inte behövs skapas här
    List <Dare> dareList = buffer.get(key);
    //IF dareList does not exist
    if (dareList == null) {
        dareList = new ArrayList<Dare>();
        dareList.add(myDare);
        buffer.put(key, (ArrayList<Dare>) dareList);
    } else {
        //If the dare is new to list
        if (!dareList.contains(myDare))
            dareList.add(myDare);
    }
   }

   public synchronized ArrayList<Dare> remove(T key) {
    return buffer.remove(key);
   }

   public synchronized ArrayList <Dare> get(T key){
    return buffer.get(key);
   }

   public synchronized ArrayList <T> getKeys() {
       return new ArrayList<>(buffer.keySet());
   }
}
