package Breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Board extends JPanel implements ActionListener, KeyListener {
	private Paddle p;
	private Ballb b;
	private Brick [] br;
	private Timer t;
	private int score;
	private int time;
	private int velx = 0;
	private int vely = 0;
	private boolean left = false;
	private boolean right = false;
	
	public Board(){
		init();
	}
	
	public void init(){
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setTitle("Break Out");
		Rectangle bounds = new Rectangle(500,300);
		this.setPreferredSize(new Dimension(500,300));
		this.setBackground(Color.BLACK);
		br = new Brick[40];
		int xSiz = 45, ySiz = 25, xPos = 37, yPos = 20, space = 2, k = 0;
		for(int i = 0; i < 4; i++){
			for (int j = 0; j < 10; j ++){
				br[k] = new Brick(xSiz, ySiz, xPos, yPos);
				k++;
				xPos = xPos + xSiz + space;
			}
			xPos = 37;
			yPos += ySiz  + space;
		}
		score = 40;
		time = 0;
		t = new Timer(5, this);
		addKeyListener(this);
		setFocusable(true);
		this.setVisible(true);
		p = new Paddle(75, 5, 250, 290, bounds);
		b = new Ballb(244, 150, 12, bounds);
		t.setInitialDelay(1);
		t.start();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		/*int k = 0;
		int brickWidth = (this.getBounds().width - 20) /10;
		for(int i = 0; i < 4; i++){
			for (int j = 0; j < 10; j++){
				br[k] = new Brick(brickWidth, 10, 10 + (brickWidth / 2) + (j * brickWidth), 
						5 + i * 10, 1 +1);
				k++;
			}
		}*/
		for (int i = 0; i < 40; i++){
			br[i].draw(g);
		}
		p.draw(g);
		b.draw(g);
//		g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
//		g.setColor(Color.GREEN);
//		g.fillRect(p.getXPos()-p.getXSiz()/2, p.getYPos()-p.getYSiz()/2, p.getXSiz(), p.getYSiz());
//		g.setColor(Color.RED);
//		g.fillOval(b.getXPos()-b.getRadius()/2, b.getYPos()-b.getRadius()/2, b.getRadius(), b.getRadius());
//		g.setColor(Color.YELLOW);
	}
	private void step(){
		if(left)
			p.move(-1.1);
		if(right)
			p.move(1.1);
		b.step();
		if(score==0){
			t.stop();
			JOptionPane.showMessageDialog(this.getParent(), "Game Over. Your Time: " 
											+ time / 200.0 + " seconds");
			init();
		}
		if(b.isOutOfBounds()){
			t.stop();
			left = false;
			right = false;
			JOptionPane.showMessageDialog(this.getParent(), "Game Over. Your Time: " + 
											(time + 2000 * score) / 200.0 + " seconds");
			init();
		}
		b.checkOutOfBounds();
		time ++;
		for(int i =0; i < 40; i ++){
			if (b.intersects(br[i])){
				br[i] = new Brick(0, 0, -1, -1);
				score --;
				b.speedUp(.01);
			}
		}
		b.intersects(p);
		repaint();
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			right = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			left = false;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			right = false;
	}

	public void keyTyped(KeyEvent arg0) {}
	
	public void actionPerformed(ActionEvent event) {
		step();
	}
}