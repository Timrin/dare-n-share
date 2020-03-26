package Server;

import dareSetUp.Dare;
import model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerController extends Thread {

    private ServerSocket serverSocket;
    private int port;
    private Buffer<Dare> dareBuffer;
    private HashMap<User, ClientConnection> userMap;
    private UserDareMap userDareMap;


    public ServerController(int port){
        this.port = port;
        dareBuffer = new Server.Buffer<>();
        userMap = new HashMap<>();
        userDareMap = new UserDareMap();
        start();
        new DareDispatcher().start();
    }

    public void addDare(Dare dare){
        dareBuffer.put(dare);
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
                new ClientHandler(socket).start();
                System.out.println("New client connected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ClientHandler extends Thread{
        private Socket socket;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        private User user;

        public ClientHandler(Socket socket){
            this.socket = socket;
            try{
                ois = new ObjectInputStream(socket.getInputStream());
                oos = new ObjectOutputStream(socket.getOutputStream());
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                user = (User) ois.readObject();
                System.out.println(user.getName() + " has connected");
                userMap.put(user,new ClientConnection(ois, oos));
                userDareMap.put(user);

                while(!socket.isClosed()){
                    Object obj = ois.readObject();
                    System.out.println("Object received");

                    if(obj instanceof Dare){
                        System.out.println("Object identified as Dare");
                        addDare((Dare) obj);
                    }
                    else if(obj instanceof User){
                        //TODO: Not sure if this is needed in this version
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class DareDispatcher extends Thread{

        @Override
        public void run() {
            while(true){
                try{
                    Dare dare = dareBuffer.get();
                    if(userMap.containsKey(dare.getChallenged().getUser())) {
                        ObjectOutputStream oos = userMap.get(dare.getChallenged().getUser()).getOos();
                        oos.writeObject(dare);
                        oos.flush();
                        System.out.println("Dare sent to something");
                    }
                    if(userMap.containsKey(dare.getInstigator().getUser())){
                        ObjectOutputStream oos2 = userMap.get(dare.getInstigator().getUser()).getOos();
                        oos2.writeObject(dare);
                        oos2.flush();
                        System.out.println("Dare sent to someone else idk");
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ServerController controller = new ServerController(2324);
    }
}
