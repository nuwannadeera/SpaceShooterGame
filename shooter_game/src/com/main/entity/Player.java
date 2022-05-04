package com.main.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import com.main.Shooter;

public class Player extends GameObject {
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, xPos, yPos, width, height, null);
	}

	/**
	 *
	 * @param xPos
	 * @param yPos
	 * @param height
	 * @param width
	 * @param img
	 */
		public Player(final int xPos, final int yPos, final int width, final int height, final String img) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
		this.rect = new Rectangle(xPos, yPos, width, height);
		this.img = getImage(img);
	}

	@Override
	public void update(final Shooter shooter) {
			if (shooter.player1Up) {
				if (!(yPos < 0)) {
					try {
						Thread.sleep(9);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					yPos--;
					rect.y--;	
				}
			} else if(shooter.player1Down) {
					if(!(yPos > shooter.getHeight() - 50)) {
					try {
						Thread.sleep(9);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					yPos++;
					rect.y++;
				}
			}
	}

	@Override
	Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}

}
