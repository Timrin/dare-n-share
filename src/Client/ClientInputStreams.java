package Client;

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

    public ClientInputStreams(Socket socket) {
        this.socket = socket;

        try {
            ois = new ObjectInputStream(socket.getInputStream());
            ois.readObject(); //TODO skriva vilket objekt som ska läsas? Hur ska vi göra paket av dares?
            //controller.newDare();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
