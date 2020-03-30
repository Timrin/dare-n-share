package Client;

import dareSetUp.Dare;

import dareSetUp.Score;
import model.User;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class intends to write objects of a user and a dare and send this request dare to the server through the client.
 * This class will connect its objectOutputStream to the Clients socket.
 */

public class ClientOutputStreams extends Thread {
	private ObjectOutputStream oos;
	private User user;

	/**
	 * Will connect objectOutputStream to clients socket when initiated.
	 *
	 * @param socket socket of the server connection
	 */
	public ClientOutputStreams(User user, Socket socket) {
		this.user = user;
		try {
			if (oos == null) {
				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(this.user);
				System.out.println(user + "OutputClient");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * User to pass dares to the server
	 *
	 * @param dare dare that will be sent to the server
	 */
	public void sendDare(Dare dare) {
		try {
			oos.writeObject(dare);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Used to pass score to the server
	 *
	 * @param score score that will be sent to the server
	 */
	public void sendScore(Score score) {
		try {
			oos.writeObject(score);
		} catch (IOException e) {
		}
	}


}
