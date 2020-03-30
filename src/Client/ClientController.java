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

    private Score score;
    private DareController dareController;

    /**
     * All methods that is required to enable users to interact with one another, and to transmit dares
     */
    public ClientController(User user, DareController dareController) {
        this.user = user;
        this.dareController= dareController;
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
    private void startSend() {
       outputStream = new ClientOutputStreams(user, connection);
    }

    /**
     * Is called when "request of challenge is made". Is to be called in controller to connect to actual user.
     * @param
     */
    private void startReceive() {
        inputStream = new ClientInputStreams(user, this, connection);
        inputStream.start();
    }

    /**
     * Set the active dare in the controller
     * @param dare
     */
    public void setDare(Dare dare) {
        dareController.setDarePanelData(dare);
    }

    /**
     * Send a created dare to the server
     * @param dare
     */
    public void sendCreatedDare(Dare dare) {
        outputStream.sendDare(dare);
    }


    //TODO: Implement score functionality

    public void setScore(Score score) {
        this.score = score;
    }

    public void sendScore (Score score){
        outputStream.sendScore(score);
    }


}
