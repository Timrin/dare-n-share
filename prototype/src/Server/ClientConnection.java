package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Objects of this class are used to save streams for later use.
 */
public class ClientConnection {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ClientConnection(ObjectInputStream ois, ObjectOutputStream oos){
        this.ois = ois;
        this.oos = oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }
}
