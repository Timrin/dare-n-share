package Server;

import dareSetUp.Dare;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController extends Thread {

    private Server.ServerInputStream inputStream;
    private Server.ServerOutputStream outputStream;
    private ServerSocket serverSocket;
    private int port;
    private Server.Buffer<Dare> buffer;


    public ServerController(int port){
        this.port = port;
        buffer = new Server.Buffer<>();
    }

    public void addDare(Dare dare){
        buffer.put(dare);
    }
    @Override
    public void run() {
        try{
            serverSocket = new ServerSocket(port);
        }catch (IOException e){
            e.printStackTrace();
        }
        while(true){
            try {
                Socket socket = serverSocket.accept();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class DareSender extends Thread{
        
    }
}
