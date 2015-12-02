package item;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

public class Ball extends PongItem {

	/**
	 * Speed of ball (in pixels per second)
	 */
	public static final int BALL_SPEED = 2;
	
	public Ball(){
		Image ball;
		
		ball = Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("image/ball.png"));
		icon = new ImageIcon(ball);
		width = icon.getIconWidth();
		height = icon.getIconHeight();
		
		speed = new Point(BALL_SPEED, BALL_SPEED);
		position = new Point(150, 150);
	}
	
	public String toString(){
		return ("B0" + super.toString() + "H" + getSpeedX() + "V" + getSpeedY());
	}
	
	public void update(String paquet){
		StringTokenizer st = new StringTokenizer(paquet, "/");
		while(st.hasMoreTokens()){
			String str = st.nextToken();
			if(str.contains("B")){
				st = new StringTokenizer(str, "BXYHV");
				st.nextToken();
				this.setPositionX(Integer.parseInt(st.nextToken()));
				this.setPositionY(Integer.parseInt(st.nextToken()));
				this.setSpeedX(Integer.parseInt(st.nextToken()));
				this.setSpeedY(Integer.parseInt(st.nextToken()));
			}
		}
	}
}
