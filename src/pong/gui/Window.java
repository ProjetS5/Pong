package pong.gui;

import javax.swing.JFrame;

/**
 * A Window is a Java frame containing an Pong
 */
public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Pong component to be displayed
	 */
	private final Pong pong;

	/**
	 * Constructor
	 */
	public Window(Pong pong) {
		this.pong = pong;
		this.addKeyListener(pong);
	}

	/**
	 * Displays the Window using the defined margins, and call the
	 * {@link Pong#animate()} method of the {@link Pong} every 100ms
	 */
	public void displayOnscreen() {
		add(pong);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void play(){
		while(pong.getStatus()) {
			pong.play();
			try {
				Thread.sleep(Pong.timestep);
			} catch (InterruptedException e) {};
		}
	}
}
