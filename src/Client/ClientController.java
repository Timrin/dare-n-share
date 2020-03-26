package Client;

import dareSetUp.Dare;
import model.User;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientController {
    private Socket connection;
    private String ip;
    private int port;
    private User user;
    private ClientInputStreams inputStream;
    private ClientOutputStreams outputStream;

    private BufferOfDares <Dare> dareBuffer;
    private Dare dare;

    /**
     * All methods that is required to able contacts to interact with one another, and to transmit messages
     */

    public ClientController(User user, String ip, int port) {
        this.user = user;
        this.ip = ip;
        this.port = port;
        try {
            connection = new Socket(ip, port);
            startSend();
            startReceive();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates objects of ClientObjectOutputStreams, connects through socket and initiates its thread. This method will be called in controller to connect to actual user
     * @param
     */

    private void startSend() { //Todo detta kanske ändras beroende på vad det är som ska skickas
       outputStream = new ClientOutputStreams(user, connection);
       outputStream.start();
    }

    /**
     * Is called when "request of challenge is made". Is to be called in controller to connect to actual user.
     * @param
     */

    private void startReceive() { //Todo detta kanske ändras beroende på vad det är som ska skickas
        inputStream = new ClientInputStreams(user, this, connection);
        outputStream.start();
    }

    public void setDare(Dare dare) {
        this.dare = dare; //Todo send dare to other controller
    }

    public void sendDare(Dare dare) {
        outputStream.sendDare(dare);
    }


}
