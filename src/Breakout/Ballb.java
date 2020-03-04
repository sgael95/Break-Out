package Breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ballb {
	private double trueX;
	private double trueY;
	private double xSpd;
	private double ySpd;
	private int dia;
	private int xPos;
	private int yPos;
	private int ytime = -1;
	private int xtime = -1;
	private Rectangle bounds;
	
	public Ballb(int x, int y, int dia, Rectangle bounds){
		trueX = (double)x;
		xPos = x;
		trueY = (double)y;
		yPos = y;
		xSpd = .25-Math.random()/2;
		ySpd = 1;
		this.dia = dia;
		this.bounds = bounds;
	}
	
	public void checkOutOfBounds(){
		double xBnd = bounds.width;
		double yBnd = bounds.height;
		if(trueX < 0){
			trueX = -trueX;
			xSpd = -xSpd;
		}
		if(trueY < 0){
			trueY = -trueY;
			ySpd = -ySpd;
		}
		if(trueX > xBnd){
			trueX = xBnd - (trueX - xBnd);
			xSpd = -xSpd;
		}
		if(trueY > yBnd){
			trueY = yBnd - (trueY - yBnd);
			ySpd = -ySpd;
		}
	}
	public boolean isOutOfBounds(){
		double yBnd = bounds.height;
		if(trueY > yBnd)
			return true;
		return false;
	}
	public void speedUp(double x){
		ySpd *= 1 + x;
	}
	public void step(){
		xtime--;
		ytime--;
		trueX += xSpd;
		trueY += ySpd;
		xPos = (int)trueX;
		yPos = (int)trueY;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.cyan);
		g.fillOval(xPos, yPos, dia, dia);
	}
	
	public boolean intersects(Brick br){
		boolean x = false, y = false, xin = false, yin = false;
		if (trueX > br.getXPos()-(br.getXSiz() / 2) &&
				trueX < br.getXPos()+(br.getXSiz() / 2))
			x = true;
		if (trueX + dia > br.getXPos()-(br.getXSiz() / 2) && 
				trueX+ dia < br.getXPos()+(br.getXSiz() / 2))
			x = true;
		if (trueX > br.getXPos()-(br.getXSiz() / 2) && 
				trueX < br.getXPos()+(br.getXSiz() / 2))
			xin = true;
		if(trueY > br.getYPos()-(br.getYSiz() / 2) && 
				trueY < br.getYPos()+(br.getYSiz() / 2))
			y = true;
		if(trueY + dia > br.getYPos()-(br.getYSiz() / 2) && 
				trueY + dia < br.getYPos()+(br.getYSiz() / 2))
			y = true;
		if (trueY > br.getXPos()-(br.getXSiz() / 2) && 
				trueY < br.getXPos()+(br.getXSiz() / 2))
			yin = true;
		if(x && y){
			if(xin==yin && xtime < 0 && ytime < 0){
				//xSpd *=-1;
				ySpd *=-1;
				ytime = 10;
				xtime = 10;
			}
			else if(xin){
				ySpd *= -1;
				ytime = 10;
			}
			else if(yin){
				xSpd *= -1;
				xtime = 10;
			}
		}
		return x && y;
	}
	
	public boolean intersects(Paddle p){
		boolean x = false, y = false, xin = false, yin = false;
		if (trueX > p.getXPos()-(p.getXSiz() / 2) &&
				trueX < p.getXPos()+(p.getXSiz() / 2))
			x = true;
		if (trueX + dia > p.getXPos()-(p.getXSiz() / 2) && 
				trueX + dia < p.getXPos()+(p.getXSiz() / 2))
			x = true;
		if(trueY > p.getYPos()-(p.getYSiz() / 2) && 
				trueY < p.getYPos()+(p.getYSiz() / 2))
			y = true;
		if(trueY + dia > p.getYPos()-(p.getYSiz() / 2) && 
				trueY + dia < p.getYPos()+(p.getYSiz() / 2))
			y = true;
		if(x && y){
			xSpd = ((trueX + (dia/2)) - p.getXPos())/ (p.getXSiz()/2);
			ySpd = -ySpd;
		}
		return x && y;
	}
}
