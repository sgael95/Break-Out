package Breakout;

import java.awt.*;

public class Ball {
	private int xpos;
	private int ypos;
	private Rectangle bounds;
	private int radius;
	private int xspd;
	private int yspd;
	Image image;
	Graphics graphic;
	
	public Ball(int xpos, int ypos, int radius, Rectangle bounds){
		this.xpos = xpos;
		this.ypos = ypos;
		this.radius = radius;
		this.bounds = bounds;
		xspd = 1;
		yspd = -1;
	}
	
	public void step(){
		xpos += xspd;
		ypos += yspd;
		checkOutOfBounds();
	}
	
	public int getXPos(){
		return xpos;
	}
	
	public int getYPos(){
		return ypos;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public boolean intersects(Brick br){
		boolean x = false, y = false, xin = false, yin = false;
		if (xpos - radius > br.getXPos()-(br.getXSiz() / 2) &&
				xpos - radius < br.getXPos()+(br.getXSiz() / 2))
			x = true;
		if (xpos + radius > br.getXPos()-(br.getXSiz() / 2) && 
				xpos + radius < br.getXPos()+(br.getXSiz() / 2))
			x = true;
		if (xpos > br.getXPos()-(br.getXSiz() / 2) && 
				xpos < br.getXPos()+(br.getXSiz() / 2))
			xin = true;
		if(ypos - radius > br.getYPos()-(br.getYSiz() / 2) && 
				ypos - radius < br.getYPos()+(br.getYSiz() / 2))
			y = true;
		if(ypos + radius > br.getYPos()-(br.getYSiz() / 2) && 
				ypos + radius < br.getYPos()+(br.getYSiz() / 2))
			y = true;
		if (ypos > br.getXPos()-(br.getXSiz() / 2) && 
				ypos < br.getXPos()+(br.getXSiz() / 2))
			yin = true;
		if(x && y){
			if(xin==yin){
				//xspd *=-1;
				yspd *=-1;
			}
			else if(xin){
				yspd *= -1;
			}
			else if(yin){
				xspd *= -1;
			}
		}
		return x && y;
	}
	
	public boolean intersects(Paddle p){
		boolean x = false, y = false, xin = false, yin = false;
		if (xpos - radius > p.getXPos()-(p.getXSiz() / 2) &&
				xpos - radius < p.getXPos()+(p.getXSiz() / 2))
			x = true;
		if (xpos + radius > p.getXPos()-(p.getXSiz() / 2) && 
				xpos + radius < p.getXPos()+(p.getXSiz() / 2))
			x = true;
		if(ypos - radius > p.getYPos()-(p.getYSiz() / 2) && 
				ypos - radius < p.getYPos()+(p.getYSiz() / 2))
			y = true;
		if(ypos + radius > p.getYPos()-(p.getYSiz() / 2) && 
				ypos + radius < p.getYPos()+(p.getYSiz() / 2))
			y = true;
		if(x && y){
			if(xin==yin){
				xspd *=-1;
				yspd *=-1;
			}
			else if(xin){
				yspd *= -1;
			}
			else if(yin){
				xspd *= -1;
			}
		}
		return x && y;
	}
	
	public boolean checkOutOfBounds(){
		System.out.println(xpos + "," + ypos);
		System.out.println(xspd + "," + yspd);
		
		if (xpos < 0)
			xspd = 1;
		if (xpos > bounds.width - radius)
			xspd = -1;
		if (ypos < 0)
			yspd = 1;
		if (ypos > bounds.height - radius){
			yspd = -1;
			return true;
		}
		return false;
	}
	
	
	public void draw(Graphics g){
		g.setColor(Color.cyan);
		g.fillOval(xpos - radius, ypos - radius, radius *2, radius *2);
	}
}
