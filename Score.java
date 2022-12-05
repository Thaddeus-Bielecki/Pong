import java.awt.*;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * class to maintain and track the game's score
 * @author Thaddeus Bielecki
 *
 */
public class Score extends Rectangle{
	
	//to hold scores of each player
	int player1;
	int player2;
	
	/**
	 * Class constructor
	 */
	Score(){

	}
	
	/**
	 * To draw the score of the game
	 * @param g - graphics object to draw the score
	 */
	public void draw (Graphics g) {
		 g.setColor(Color.white);
		 g.setFont(new Font("Consolas", Font.PLAIN, 60));
		 
		 g.drawLine((GamePanel.GAME_WIDTH / 2), 0, (GamePanel.GAME_WIDTH / 2), GamePanel.GAME_HEIGHT);
		 
		 //format the scores to look symmetrical
		 g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (GamePanel.GAME_WIDTH / 2) - 95, 50);
		 g.drawString(String.valueOf(player2/10) + String.valueOf(player2%10), (GamePanel.GAME_WIDTH / 2) + 20, 50);
	}
}
