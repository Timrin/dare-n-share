package Client;

import model.Dare;

import model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * This class aims to receive a request of a dare from a user, with a chosen receiver in mind. This request is sent from the server.
 * The objectInputStreams that reads this request challenge will be connected to the Client through its socket.
 */
public class ClientInputStreams extends Thread {
    private ObjectInputStream ois;
    private ClientController controller;


    public ClientInputStreams(User user, ClientController controller, Socket socket) {
        this.controller = controller;

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
                System.out.println(dare.toString());
                controller.setDare(dare);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
