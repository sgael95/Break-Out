package Breakout;

import java.awt.*;

public class Brick {
	private int xSiz;
	private int ySiz;
	private int xpos;
	private int ypos;
	
	public Brick(int xSiz, int ySiz, int xpos, int ypos){
		this.xSiz = xSiz;
		this.ySiz = ySiz;
		this.xpos = xpos;
		this.ypos = ypos;
	}
	
	public int getXSiz(){
		return xSiz;
	}
	
	public int getYSiz(){
		return ySiz;
	}
	
	public int getXPos(){
		return xpos;
	}
	
	public int getYPos(){
		return ypos;
	}
	 
	 public void draw(Graphics g){
		 g.setColor(Color.RED);
		 g.fillRect(xpos - (xSiz / 2), ypos -(ySiz / 2), xSiz, ySiz);
	 }

}
