package Client;

import dareSetUp.User;

import java.net.Socket;

/**
 * Class that can interact with server though sending and receiving
 */

public class Client {
    private Socket connection;
    private String ip;
    private int port;
    private User user;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    /**
     * Creates objects of ClientObjectOutputStreams, connects through socket and initiates its thread. This method will be called in controller to connect to actual user
     * @param user
     */

    public void startSend(User user) { //Todo detta kanske ändras beroende på vad det är som ska skickas
        this.user = user;
        connection = new Socket();
       // new ClientOutputStreams(connection).start();
    }

    /**
     * Is called when "request of challenge is made". Is to be called in controller to connect to actual user.
     * @param user
     */

    public void startReceive(User user) { //Todo detta kanske ändras beroende på vad det är som ska skickas
            this.user = user;
            connection = new Socket();
          //  new ClientInputStreams(connection).start();
    }
}
