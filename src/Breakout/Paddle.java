package Breakout;

import java.awt.*;

public class Paddle{
	private int xSiz;
	private double trueX;
	private int ySiz;
	private int xpos;
	private int ypos;
	private Rectangle bounds;
	
	public Paddle(int xSiz, int ySiz, int xpos, int ypos, Rectangle bounds){
		this.xSiz = xSiz;
		this.ySiz = ySiz;
		this.xpos = xpos;
		this.ypos = ypos;
		this.bounds = bounds;
		trueX = xpos;
	}
	
	public void move(double x){
		this.trueX += x;
		outOfBounds();
		xpos = (int)trueX;
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
	
	public boolean outOfBounds(){
		if (trueX < 0)
			trueX = -trueX;
		if (trueX > bounds.width)
			trueX = bounds.width - (trueX - bounds.width);
		return false;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(xpos - xSiz / 2, ypos - ySiz / 2, xSiz, ySiz);
	}

}
