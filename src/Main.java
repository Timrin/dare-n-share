import gui.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

	public static void main(String[] args) {
		startWindow();
		startWindow();
	}

	private static void startWindow() {
		JFrame frame = new JFrame("Challenger");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.PINK);
		frame.setPreferredSize(new Dimension(400,520));
		frame.pack();
		frame.add(new LoginPanel());
		frame.setVisible(true);
	}

}
