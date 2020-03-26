package Client;

import dareSetUp.Dare;

import model.User;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class intends to write objects of a user and a dare and send this request dare to the server through the client.
 * This class will connect its objectOutputStream to the Clients socket.
 */

public class ClientOutputStreams extends Thread {
    private ObjectOutputStream oos;
    private Socket socket;
    private User user;
    /**
     * Will connect objectOutputStream to clients socket when initiated.
     *
     * @param socket
     */
    public ClientOutputStreams(User user,Socket socket) {
        this.user = user;
        this.socket = socket;
        try {
            if (oos == null) {
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(this.user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDare(Dare dare) {
        try {
            oos.writeObject(dare);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
