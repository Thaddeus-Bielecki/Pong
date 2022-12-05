import java.awt.*;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * To represent the window of the gmae
 * @author Thaddeus Bielecki
 *
 */
public class GameFrame extends JFrame {
	
	GamePanel panel;
	
	/**
	 * Constructor to set up the window and initialize its instance of the game panel
	 */
	GameFrame(){
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
