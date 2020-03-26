package Client;

import dareSetUp.Dare;
import dareSetUp.DareController;
import dareSetUp.Score;
import model.User;


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientController {
    private Socket connection;
    private final static String ip = "127.0.0.1";
    private final static int port=2324;
    private User user;
    private ClientInputStreams inputStream;
    private ClientOutputStreams outputStream;

    private BufferOfDares <Dare> dareBuffer;
    private Dare dare;
    private Score score;
    private DareController dareController;

    /**
     * All methods that is required to able contacts to interact with one another, and to transmit messages
     */

    public ClientController(User user) {
        this.user = user;
        //this.ip = ip;
        //this.port = port;
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
      // outputStream.start();
    }

    /**
     * Is called when "request of challenge is made". Is to be called in controller to connect to actual user.
     * @param
     */
    private void startReceive() { //Todo detta kanske ändras beroende på vad det är som ska skickas
        inputStream = new ClientInputStreams(user, this, connection);
        inputStream.start();
    }

    public void setDare(Dare dare) {
        this.dare = dare; //Todo send dare to other controller
        dareController.getDareFromClient(dare);
    }

    public void setOpponent(User user){
        // Sever sends opponent user with dare?
        // Set user at dareController.setOpponent(User user) ?
    }

    public void sendDare(Dare dare) {
        outputStream.sendDare(dare);
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void sendScore (Score score){
        outputStream.sendScore(score);
    }


}
