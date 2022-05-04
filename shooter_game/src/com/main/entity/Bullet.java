package com.main.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import com.main.Difficulty;
import com.main.Shooter;

public class Bullet extends GameObject {
	
	public static final int UNIT_SIZE = 20;
	
	private int deltaX;


	public Bullet(final int deltaX ,final int xPos, final int yPos, final int width, final int height, final String img) {
		this.deltaX = deltaX;
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
		this.rect = new Rectangle(xPos, yPos, width, height);
		this.img = getImage(img);
	}
	
	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, xPos, yPos, width, height, null);
	}

	@Override
	public void update(final Shooter shooter) {
		if (xPos < 5 || xPos > 500) {
			shooter.bullets.remove(this);

		} else {
			xPos += deltaX;
			rect.x += deltaX;

			if ((rect.x == shooter.ballX) && ((yPos-20 <= shooter.ballY) && (yPos+10 >= shooter.ballY))) {
	            shooter.marks++;
	            Shooter.second = Difficulty.diffTime;
				shooter.newBall();
	        } else if ((rect.x == shooter.ballX1) && ((yPos-20 <= shooter.ballY1) && (yPos+10 >= shooter.ballY1))) {
	        	shooter.errorMarks++;
	        } else if ((rect.x == shooter.ballX2) && ((yPos-20 <= shooter.ballY2) && (yPos+10 >= shooter.ballY2))) {
	        	shooter.errorMarks++;
	        }
		}
	}

	@Override
	Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}

}
