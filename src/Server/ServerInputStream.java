package Server;

import dareSetUp.Dare;
import model.User;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerInputStream extends Thread {
    private ObjectInputStream ois;
    private User user;
    private ServerController controller;

    public ServerInputStream(Socket socket, ServerController controller){
        this.controller = controller;
        try{
            ois = new ObjectInputStream(socket.getInputStream());
            user =  (User) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                Dare dare = (Dare) ois.readObject();
                controller.addDare(dare);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
