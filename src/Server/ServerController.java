package Server;

import dareSetUp.Dare;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController extends Thread {

    private ServerInputStream inputStream;
    private ServerOutputStream outputStream;
    private ServerSocket serverSocket;
    private int port;
    private Buffer<Dare> buffer;


    public ServerController(int port){
        this.port = port;
        buffer = new Buffer<>();
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
