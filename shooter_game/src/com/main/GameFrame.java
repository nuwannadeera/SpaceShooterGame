package com.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameFrame() {

		this.add(new Shooter(this));
		this.setTitle("Space Shooter Game");
		this.setPreferredSize(new Dimension(600,600));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		Image icon = Toolkit.getDefaultToolkit().getImage("images/rocket.png");  
		this.setIconImage(icon);  
		
	}

}
