package item;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

public class Racket extends PongItem {
	
	public Racket(Point position){
		Image racket;
	
		racket = Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("image/racket.png"));
		this.icon = new ImageIcon(racket);
		this.width = icon.getIconWidth();
		this.height = icon.getIconHeight();
		
		this.speed = new Point(0, 0);
		this.position = position;
	}

	public String toString(){
		return ("R" + super.toString());
	}
	
	public void update(String paquet){
		StringTokenizer st = new StringTokenizer(paquet, "/");
		while(st.hasMoreTokens()){
			String str = st.nextToken();
			if(str.contains("R")){
				st = new StringTokenizer(str, "RXY");
				this.setPositionX(Integer.parseInt(st.nextToken()));
				this.setPositionY(Integer.parseInt(st.nextToken()));
			}
		}
	}
}
