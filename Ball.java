import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Represents the ball object in the Pong game
 * @author Thaddeus Bielecki
 *
 */
public class Ball extends Rectangle{
	
	//instance of random class to be used to spawn in the ball
	Random random;
	//to hold the ball's current velocity
	int xVelocity;
	int yVelocity;
	//starting speed
	int initialSpeed = 3;
	
	/**
	 * Ball class constructor
	 * @param x - the x coordinate of the ball
	 * @param y - the y coordinate of the ball
	 * @param width - the dimension of the ball
	 * @param height - the dimension of the ball
	 */
	Ball(int x, int y, int width, int height){
		//pass to parent class
		super(x, y, width, height);
		
		//choose a random X direction for the ball to travel
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0) {
			randomXDirection--;
		}
		setXDirection(randomXDirection * initialSpeed);
		
		//choose a random Y direction for the ball
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0) {
			randomYDirection--;
		}
		setYDirection(randomYDirection * initialSpeed);
	}
	
	/**
	 * To update the ball's x velocity
	 * @param randomXDirection - the desired new velocity
	 */
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}
	
	/**
	 * To update the ball's y velocity
	 * @param randomYDirection - the desired new velocity
	 */
	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
	}
	
	/**
	 * Move the ball by incrementing the x and y position with its velocity
	 */
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	
	/**
	 * draw the ball
	 * @param g - graphics object to draw the ball with
	 */
	public void draw (Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, width, height);
	}
}
