package Server;

import dareSetUp.Dare;
import dareSetUp.DontEatMeat;
import model.User;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FakeClient extends Thread {
    private int port;
    private String ip;
    private User user;

    public FakeClient(int port, String ip){
        this.port = port;
        this.ip = ip;
        user = new User("Greger", new ImageIcon(), "Greger@email.com");
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(ip, port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            oos.writeObject(user);

            sleep(3000);

            oos.writeObject(new DontEatMeat(user, new User()));

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
}
