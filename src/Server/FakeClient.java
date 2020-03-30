package Server;

import model.Dare;
import dareSetUp.DontEatMeat;
import model.User;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class serves to test data flow through the server. Will be scrapped.
 */
public class FakeClient extends Thread {
    private int port;
    private String ip;
    private User user;

    public FakeClient(int port, String ip){
        this.port = port;
        this.ip = ip;
        user = new User("Greger", new ImageIcon(), "Greger@email.com");
        start();
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(ip, port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connection established");

            oos.writeObject(user);
            System.out.println("User has been sent");

            sleep(3000);

            oos.writeObject(new DontEatMeat(user, new User()));
            System.out.println("Dare has been sent");

            while(true){
                Object obj = ois.readObject();

                if(obj instanceof Dare){
                    Dare dare = (Dare) obj;
                    System.out.println(dare.getInstigator().getUser().getName());
                }
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FakeClient faker = new FakeClient(2324, "127.0.0.1");
    }
}
