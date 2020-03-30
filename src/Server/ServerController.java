package Server;

import model.Dare;
import model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * This class acts as the entire server. It holds two inner classes, ClientHandler, which handles
 * each connected client, and DareDispatch, which continuously tries to send out Dares from a buffer.
 * When a client connects to the server, a ClientHandler is created. The client sends its user, which
 * which is saved with its InputStream and OutputsStream in a HashMap. The user is also saved alongside a
 * list of Dares in the custom HashMap class UserDareMap.
 */
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

    /**
     * Starts a new ClientHandler when a new client connects. Sends along its socket.
     */
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

    /**
     * Handles connection with a single client. Establishes InputStreams and OutputStreams.
     */
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

        /**
         * First reads in a User object and saves it. Then listens for new messages (Users/Dares).
         */
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
                        //TODO: Friend request functionality
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This Thread tries to get a Dare from a Buffer. When that happens, it identifies the
     * Users connected to that Dare, and sends the Dare to them both.
     */
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
                        System.out.println("Dare sent to challenged user");
                        //TODO: This will never happen until Users has access to other Users
                    }
                    if(userMap.containsKey(dare.getInstigator().getUser())){
                        ObjectOutputStream oos2 = userMap.get(dare.getInstigator().getUser()).getOos();
                        oos2.writeObject(dare);
                        oos2.flush();
                        System.out.println("Dare sent to back to challenging user");
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
