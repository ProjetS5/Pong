package pong.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Setting extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Width of option area
	 */
	private static final int SIZE_OPTION_X = 200;
	/**
	 * Height of option area		
	 */
	private static final int SIZE_OPTION_Y = 300;
	
	public Setting(){
		
		JButton b = new JButton("Test");
		this.add(b);
		
		setPreferredSize(new Dimension(SIZE_OPTION_X, SIZE_OPTION_Y));
		this.setVisible(true);
		super.paintComponents(this.getGraphics());
}

}
