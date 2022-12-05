import java.awt.*;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
/**
 * To represent the paddles used within the game
 * @author Thaddeus Bielecki
 *
 */
public class Paddle extends Rectangle{
	
	int id; //distinguish which player the paddle belongs to
	int yVelocity; //the velocity the paddle is moving with
	int speed = 10; // the speed of the paddle
	
	/**
	 * Constructor to initialize the paddle object
	 * @param x - the x position of the paddle
	 * @param y - y position of the paddle
	 * @param PADDLE_WIDTH - set the width dimension of the paddle
	 * @param PADDLE_HEIGHT - set the height dimension of the paddle
	 * @param id - determine which player the paddle belongs to
	 */
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
	}
	
	/**
	 * To move the paddles up/down based on which player they belong to
	 * @param e - represents the key pressed
	 */
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1: //player 1
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-speed); //move up
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(speed); //move down
				move();
			}
			break;
		case 2: //player 2
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed); //move up
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed); //move down
				move();
			}
			break;
		}
	}
	
	/**
	 * To stop the paddles from continuing to move once the key is no longer pressed
	 * @param e - represents the key that is no longer being pressed
	 */
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1: //player 1
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0); //reset the direction of the objects movement
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0); //reset the direction of the objects movement
				move();
			}
			break;
		case 2: //player 2
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0); //reset the direction of the objects movement
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0); //reset the direction of the objects movement
				move();
			}
			break;
		}		
	}
	
	/**
	 * update the direction the paddle is moving
	 * @param yDirection - the new direction to move the paddle
	 */
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	
	/**
	 * move the paddle by adding its velocity to its position
	 */
	public void move() {
		y = y + yVelocity;
	}
	
	/**
	 * draw the paddles
	 * @param g - graphics object
	 */
	public void drawP(Graphics g) {
		if(id == 1) {
			g.setColor(Color.blue);
		} else {
			g.setColor(Color.red);
		}
		g.fillRect(x, y, width, height);
	}
}
