package item;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import pong.gui.Pong;

public abstract class PongItem {
	
	/**
	 * Width of the item in pixels
	 */
	protected int width;
	/**
	 * Height of the item in pixels
	 */
	protected int height;
	/**
	 * Speed of item, in pixels per timestamp
	 */
	protected Point speed;
	/**
	 * Position of item
	 */
	protected Point position = new Point(0, 0);
	/**
	 * Item to be displayed
	 */
	protected ImageIcon icon;
	
	/**
	 * 
	 * maximum tolerance between positions
	 */
	protected int tolerance;
	
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getSpeedX(){
		return (int) speed.getX();
	}
	
	public int getSpeedY(){
		return (int) speed.getY();
	}
	
	public void setSpeedX(int x){
		speed.x= x;
	}
	
	public void setSpeedY(int y){
		speed.y= y;
	}
	
	public int getPositionX(){
		return (int) position.getX();
	}
	
	public int getPositionY(){
		return (int) position.getY();
	}
	
	public void setPositionX(int x){
		position.x = x;
	}
	
	public void setPositionY(int y){
		position.y = y;
	}
	
	public void setPosition(int x, int y){
		position.x = x;
		position.y = y;
	}

	public void translate(int speedX, int speedY){
		position.translate(speedX, speedY);
	}
	
	public Image getImage(){
		return icon.getImage();
	}
	
	/**
	 * return true if there is impact
	 */
	public boolean contact(PongItem p){
		if ((this.getPositionX() <= p.getPositionX() + p.getWidth())
				&& (this.getPositionX() >= p.getPositionX() - this.getWidth())
				&& (this.getPositionY() >= p.getPositionY() - this.getHeight())
				&& (this.getPositionY() <= p.getPositionY() + p.getHeight()))
			return true;
		return false;
	}
	
	/**
	 * Center of an PongItem
	 */
	public Point getCenter(){
		Point center = (Point) position.clone();
		center.translate(width/2, height/2);
		return center;
	}
	
	public int getCenterX(){
		return (int) this.getCenter().getX();
	}
	
	public int getCenterY(){
		return (int) this.getCenter().getY();
	}
	
	public String toString(){
		return ("X" + getPositionX() + "Y" + getPositionY());
	}

	public abstract void update(String paquet);
	/**
	 * Mirror the game around X axe
	 */
	public void invHor(){
		setPositionX(Pong.SIZE_PONG_X - getPositionX() - getWidth());
		if(this instanceof Ball){
			setSpeedX(-getSpeedX());
		}
	}
}
