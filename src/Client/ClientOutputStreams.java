package Client;

import dareSetUp.Dare;
import dareSetUp.GoalDare;
import dareSetUp.TimeDare;
import dareSetUp.User;

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
    private Dare dare;
    private GoalDare goalDare;
    private TimeDare timeDare;

    /**
     * Will connect objectOutputStream to clients socket when initiated.
     * @param socket
     */
    public ClientOutputStreams(Socket socket) {
        this.socket = socket;
        try {
            if (oos == null){
                oos = new ObjectOutputStream(socket.getOutputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            oos.writeObject(user);
            while (socket.isConnected()) {
                System.out.println("Client: thread is connected");

                if (dare.equals(goalDare)) {
                    oos.writeObject(goalDare);
                    System.out.println("You have been challenged to a targeted dare");
                } else if (dare.equals(timeDare)) {
                    oos.writeObject(timeDare);
                    System.out.println("You have been challenged to a time-set dare");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
