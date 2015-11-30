package pong.gui;

import item.Ball;
import item.PongItem;
import item.Racket;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JPanel;

/**
 * An Pong is a Java graphical container that extends the JPanel class in
 * order to display graphical elements.
 */
public class Pong extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Constant (c.f. final) common to all Pong instances (c.f. static)
	 * defining the background color of the Pong
	 */
	private static final Color backgroundColor = new Color(0xFF, 0x40, 0);

	/**
	 * Width of pong area
	 */
	private static final int SIZE_PONG_X = 800;
	/**
	 * Height of pong area
	 */
	private static final int SIZE_PONG_Y = 600;
	/**
	 * Time step of the simulation (in ms)
	 */
	public static final int timestep = 10;
	/**
	 * Speed of ball (in pixels per second)
	 */
	public static final int BALL_SPEED = 2;
	/**
	 * Speed of racket (in pixels per second)
	 */
	public static final int RACKET_SPEED = 4;

	/**
	 * Pixel data buffer for the Pong rendering
	 */
	private Image buffer = null;
	/**
	 * Graphic component context derived from buffer Image
	 */
	private Graphics graphicContext = null;

	private PongItem ball;
	
	private PongItem racket0;
	private PongItem racket1;
	
	public Pong() {
		
		this.ball = new Ball();

		this.racket0= new Racket(new Point(0, 0));
		this.racket1= new Racket(new Point(SIZE_PONG_X, SIZE_PONG_Y));

		this.setPreferredSize(new Dimension(SIZE_PONG_X, SIZE_PONG_Y));
		this.addKeyListener(this);
	}
	
	/**
	 * à faire !!!!
	 */
	public void animatebis(BufferedReader in, PrintWriter out){
		try {
			animate();
			out.println(racket0.toString() + "/" + ball.toString());
			String paquet = in.readLine();
			racket1.update(paquet);
			racket1.invHor(SIZE_PONG_X);
			if (ball.getPositionX() > SIZE_PONG_X/2){
				ball.update(paquet);
				ball.invHor(SIZE_PONG_X);
			}
			/* And update output */
			updateScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
public void f1(BufferedReader in, PrintWriter out){
		animate();
		out.println(racket0.toString() + "/" + ball.toString());
}

public void f2(BufferedReader in, PrintWriter out){
	try{
		String paquet = in.readLine();
		ball.update(paquet);
		ball.invHor(SIZE_PONG_X);
	}catch (IOException e){
		e.printStackTrace();
	}
}

	/**
     * Proceeds to the movement of the ball and updates the screen
	 */
	public void animate() {
		/* Update ball position */
		ball.translate(ball.getSpeedX(), ball.getSpeedY());
		/* gère le rebond contre le mur */
		if (ball.getPositionX() < 0)
		{
			ball.setPositionX(0);
			ball.setSpeedX(-ball.getSpeedX());
		}
		if (ball.getPositionX() > SIZE_PONG_X - ball.getWidth())
		{
			ball.setPositionX(SIZE_PONG_X - ball.getWidth());
			ball.setSpeedX(-ball.getSpeedX());
		} 
		if (ball.getPositionY() < 0)
		{
			ball.setPositionY(0);
			ball.setSpeedY(-ball.getSpeedY());
		}
		if (ball.getPositionY() > SIZE_PONG_Y - ball.getHeight())
		{
			ball.setPositionY(SIZE_PONG_Y - ball.getHeight());
			ball.setSpeedY(-ball.getSpeedY());
		}
		/* permet que la balle passe à travers un mur (pour les tests) 
		if (ball.getPositionX() <0)
			ball.setPositionX(SIZE_PONG_X);
		if (ball.getPositionX() > SIZE_PONG_X)
			ball.setPositionX(0); */
		
		/* Gestion de l'impact */
		if (ball.contact(racket0))
		{
			if ((ball.getCenterX() > racket0.getPositionX()) 
					&& (ball.getCenterX() < racket0.getPositionX() + racket0.getWidth()))
				ball.setSpeedY(-ball.getSpeedY());
			else if ((ball.getCenterY() > racket0.getPositionY()) 
					&& (ball.getCenterY() < racket0.getPositionY() + racket0.getHeight()))
				ball.setSpeedX(-ball.getSpeedX());
			else {
				ball.setSpeedX(-ball.getSpeedX());
				ball.setSpeedY(-ball.getSpeedY());
			}
		}
		
		/* Update racket position */
		racket0.setPositionY(racket0.getPositionY() + racket0.getSpeedY());
		if (racket0.getPositionY() < 0)
			racket0.setPositionY(0);
		if (racket0.getPositionY() > SIZE_PONG_Y - racket0.getHeight()/2)
			racket0.setPositionY(SIZE_PONG_Y - racket0.getHeight()/2);
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				racket0.setSpeedY(-RACKET_SPEED);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				racket0.setSpeedY(RACKET_SPEED);
				break;
			default:
				System.out.println("got press "+e);
		}
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				racket0.setSpeedY(0);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				racket0.setSpeedY(0);
				break;
			default:
				System.out.println("got release "+e);
		}
	}
	public void keyTyped(KeyEvent e) { }

	/*
	 * (non-Javadoc) This method is called by the AWT Engine to paint what
	 * appears in the screen. The AWT engine calls the paint method every time
	 * the operative system reports that the canvas has to be painted. When the
	 * window is created for the first time paint is called. The paint method is
	 * also called if we minimize and after we maximize the window and if we
	 * change the size of the window with the mouse.
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}

	/**
	 * Draw each Pong item based on new positions
	 */
	public void updateScreen() {
		if (buffer == null) {
			/* First time we get called with all windows initialized */
			buffer = createImage(SIZE_PONG_X, SIZE_PONG_Y);
			if (buffer == null)
				throw new RuntimeException("Could not instanciate graphics");
			else
				graphicContext = buffer.getGraphics();
		}
		/* Fill the area with blue */
		graphicContext.setColor(backgroundColor);
		graphicContext.fillRect(0, 0, SIZE_PONG_X, SIZE_PONG_Y);

		/* Draw items */
		graphicContext.drawImage(ball.getImage(), ball.getPositionX(), ball.getPositionY(), ball.getWidth(), ball.getHeight(), null);
		graphicContext.drawImage(racket0.getImage(), racket0.getPositionX(), racket0.getPositionY(), racket0.getWidth(), racket0.getHeight(), null);
		graphicContext.drawImage(racket1.getImage(), racket1.getPositionX(), racket1.getPositionY(), racket1.getWidth(), racket1.getHeight(), null);
		this.repaint();
	}
}
