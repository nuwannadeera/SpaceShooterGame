package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Difficulty extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton login = new JButton("Play");
    JPanel panel = new JPanel();
    JLabel title=new JLabel("Easy");
    JLabel title1=new JLabel("Normal");
    JLabel title2=new JLabel("Hard");
    JLabel mainTitle=new JLabel("Math Space Shooter");
    public static int diffTime = 20;

    public Difficulty() {
        super("Difficulty");
        setSize(600, 600);
        setLocation(500, 100);
        setResizable(false);
        panel.setLayout(null);
        panel.setBackground(Color.black);
        Image icon = Toolkit.getDefaultToolkit().getImage("images/rocket.png");  
		this.setIconImage(icon);  
        
        login.setBounds(220, 420, 150, 30);
        login.setBackground(Color.YELLOW);
        login.setForeground(Color.BLACK);
        login.setFont(new Font("Arial", Font.BOLD, 18));

        title.setBounds(250, 230, 350, 30);
        title.setText("Easy");
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        title1.setBounds(250, 290, 350, 30);
        title1.setText("Normal");
        title1.setForeground(Color.white);
        title1.setFont(new Font("Arial", Font.BOLD, 20));

        title2.setBounds(250, 350, 350, 30);
        title2.setText("Hard");
        title2.setForeground(Color.white);
        title2.setFont(new Font("Arial", Font.BOLD, 20));


        mainTitle.setBounds(150, 80, 350, 70);
        mainTitle.setText("Math Space Shooter");
        mainTitle.setForeground(Color.YELLOW);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 32));

        panel.add(login);
        panel.add(title);
        panel.add(title1);
        panel.add(title2);
        panel.add(mainTitle);
        
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionBTN();
    }
    
    public void actionBTN() {
    	
        login.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent ae) {
                GameFrame game=new GameFrame();
                game.setVisible(true);
                dispose();
            }
        });

        title.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	Shooter.second = 60;
            	diffTime = Shooter.second;
                title.setForeground(Color.RED);
                title1.setForeground(Color.white);
                title2.setForeground(Color.white);
            }
        });

        title1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	Shooter.second = 15;
            	diffTime = Shooter.second;
                title.setForeground(Color.white);
                title1.setForeground(Color.RED);
                title2.setForeground(Color.white);
            }
        });


        title2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	Shooter.second = 12;
            	diffTime = Shooter.second;
                title.setForeground(Color.white);
                title1.setForeground(Color.white);
                title2.setForeground(Color.RED);
            }
        });

    }
}
