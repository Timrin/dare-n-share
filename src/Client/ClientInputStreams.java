package Client;

import dareSetUp.Dare;
import dareSetUp.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * This class aims to receive a request of a dare from a user, with a chosen receiver in mind. This request is sent from the server.
 * The objectInputStreams that reads this request challenge will be connected to the Client through its socket.
 */
public class ClientInputStreams extends Thread {
    private ObjectInputStream ois;
    private Socket socket;
    private User user;
    private ClientController controller;


    public ClientInputStreams(User user, ClientController controller, Socket socket) {
        this.controller = controller;
        this.socket = socket;
        this.user = user;

        try {
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    public void run() {
        while (true) {
            try {
                Dare dare = (Dare) ois.readObject();
                controller.setDare(dare);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
