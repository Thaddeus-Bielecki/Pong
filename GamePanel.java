import java.awt.*;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * To serve as the main class to perform actions within the game
 * @author Thaddeus Bielecki
 *
 */
public class GamePanel extends JPanel implements Runnable{
	
	//constants
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	
	//thread for the game to run on
	Thread gameThread;
	//necessary visual components
	Image image;
	Graphics graphics;
	Random random;
	//player paddles
	Paddle paddle1;
	Paddle paddle2;
	//essential objects to the game
	Ball ball;
	Score score;
	
	/**
	 * The class's constructor
	 */
	GamePanel(){
		//create the paddles, ball, and score
		newPaddles();
		newBall();
		score = new Score();
		//Set up listener
		this.setFocusable(true);
		this.addKeyListener(new AL());
		//set the size of the panel
		this.setPreferredSize(SCREEN_SIZE);
		
		//create and start the game thread
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	/**
	 * To spawn in a new ball at a random y coordinate
	 */
	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
		
	}
	
	/**
	 * To create new Paddles, one for each player
	 */
	public void newPaddles() {
		paddle1 = new Paddle(0, ((GAME_HEIGHT/2)-(PADDLE_HEIGHT/2)), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle((GAME_WIDTH - PADDLE_WIDTH), ((GAME_HEIGHT/2)-(PADDLE_HEIGHT/2)), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
	}
	
	/**
	 * To paint the game
	 */
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	/**
	 * To update the drawing of the game
	 * @param g - Graphics class object to draw the game with
	 */
	public void draw(Graphics g) {
		paddle1.drawP(g);
		paddle2.drawP(g);
		ball.draw(g);
		score.draw(g);
	}
	
	/**
	 * To move the ball and player paddles
	 */
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	/**
	 * Check for any form of collision with the ball and or paddle
	 */
	public void checkCollision() {
		
		//bounce ball off paddles
		if(ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++;
			if(ball.yVelocity > 0) {
				ball.yVelocity++;
			} else {
				ball.yVelocity--;
			}
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		if(ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity--;
			if(ball.yVelocity > 0) {
				ball.yVelocity++;
			} else {
				ball.yVelocity--;
			}
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		// stops paddles at window edges
		if(paddle1.y <= 0) {
			paddle1.y = 0;
		}
		if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle1.y = (GAME_HEIGHT - PADDLE_HEIGHT);
		}
		if(paddle2.y <= 0) {
			paddle2.y = 0;
		}
		if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle2.y = (GAME_HEIGHT - PADDLE_HEIGHT);
		}
		
		//stops ball at window edges
		if(ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >= (GAME_HEIGHT - BALL_DIAMETER)){
			ball.setYDirection(-ball.yVelocity);
		}
		
		//award a point and creates new paddle and ball
		if(ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
		}
		if(ball.x > (GAME_WIDTH - BALL_DIAMETER)) {
			score.player1++;
			newPaddles();
			newBall();
		}
	}
	
	/**
	 * Function to run the game
	 */
	public void run() {
		// game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	/**
	 * Action Listener class to listen to the keyboard
	 * @author Thaddeus Bielecki
	 *
	 */
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
