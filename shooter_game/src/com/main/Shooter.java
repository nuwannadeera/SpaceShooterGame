package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.main.api.API;
import com.main.entity.Bullet;
import com.main.entity.Player;

public class Shooter extends JPanel implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Player player1;
	private Image image;
	private Graphics graphics;
	private Timer timer;
	public boolean player1Up = false;
	public boolean player1Down = false;
	public boolean running = false;
	public CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
	public static int DELAY = 75;
	public String logic;
	public int ballX;
	public int ballY;
	public int ballX1;
	public int ballY1;
	public int ballX2;
	public int ballY2;
	public String answer1;
	public String answer2;
	public String answer3; 
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	public static final int SCREEN_WIDTH1 = 520;
	public static final int SCREEN_HEIGHT1 = 540;
	public static final int UNIT_SIZE = 30;
	public int marks = 0;
	public int errorMarks = 0;
	
	private GameFrame gameFrame;
	private JLabel retryBtnText;
	
	public static int second = 60;
	public int newRandom = 0;
	public int lastRandom = 1;
	public int d = 0;
	public int d1 = 0;
	Random r = new Random();

	public Shooter(GameFrame gameFra) {
		
		gameFrame = gameFra;
		this.setFocusable(true);
		addKeyListener(this); 
		
        retryBtnText=new JLabel("Retry");
        retryBtnText.setBounds(400,400,300,30);
        retryBtnText.setText("Retry");
        retryBtnText.setForeground(Color.WHITE);
        retryBtnText.setFont(new Font("Serif", Font.BOLD, 20));
        retryBtnText.setVisible(true);
		
		player1 = new Player(10, 175, 64, 64, "images/player1.png");
		startGame();
	}
	
	private void startGame() {
        newBall();
        newBall();
        newBall();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
        countDownTimer();
        timer.start();
    }
	
	
	
	public void countDownTimer() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				second--;
				if (second == 0) {
					timer.stop();
					running = false;
				}
			}
		});
	}
	
	
	public void newBall() {
		new Random();
        d = GetNumberRandom();
        d1 = GetNumberRandom();
        logic = d + "  +  " + d1 + "   = " + " ?";
        answer1 = String.valueOf(d + d1);
        answer2 = GetNumberRandom() == (d + d1) ? String.valueOf(GetNumberRandom()) : String.valueOf(GetNumberRandom());
        answer3 = GetNumberRandom() == (d + d1) ? String.valueOf(GetNumberRandom()) : String.valueOf(GetNumberRandom());

        ballX = 300;
        ballY = GetRandom();

        ballX1 = 300;
        ballY1 = GetRandom();

        ballX2 = 300;
        ballY2 = GetRandom();

    }

	private int GetRandom(){
	   newRandom = r.nextInt(SCREEN_HEIGHT1 / UNIT_SIZE) * UNIT_SIZE;
	   while (newRandom == lastRandom){
	      newRandom = r.nextInt(SCREEN_HEIGHT1 / UNIT_SIZE) * UNIT_SIZE;
	      if(newRandom != lastRandom) {
              break;
          }
	   }
	   lastRandom = newRandom;
	   return newRandom;
	}
	
	private int GetNumberRandom(){
		   newRandom = r.nextInt(25);
		   while (newRandom == lastRandom && ((d + d1) == newRandom)){
		      newRandom = r.nextInt(25);
		      if(newRandom != lastRandom) 
		    	  break;
		   }
		   lastRandom = newRandom;
		   return newRandom;
		}

	
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();

		paintComponent(graphics);
		g.drawImage(image,0,0,null);
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		if (running) {
			for (Bullet bullet : bullets) {
				bullet.draw(g);
				bullet.update(this);
			}
		}
			try {
				drawMath(g);
			} catch (IOException e) {
				e.printStackTrace();
			}
			player1.draw(g);
			player1.update(this);
	}
	
	
	private void drawMath(Graphics g) throws IOException {
		if (running) {
			g.setColor(Color.WHITE);
	        g.fillRect(450, 290, 150, 30);
	        
	        g.setColor(Color.WHITE);
	        g.fillRect(450, 350, 150, 70);

	        g.setColor(Color.BLACK);
	        g.setFont(new Font("Serif", Font.BOLD, 25));
	        g.drawString(logic, 450, 315);
	        
	        g.setColor(Color.BLACK);
	        g.setFont(new Font("Serif", Font.BOLD, 12));
	        g.drawString("Timer", 500, 365);
	        
	        g.setColor(Color.RED);
	        g.setFont(new Font("Serif", Font.BOLD, 35));
	        g.drawString(second+"", 495, 400);

	        g.setColor(Color.red);
	        g.fillOval(ballX, ballY, UNIT_SIZE, UNIT_SIZE);

	        g.setColor(Color.WHITE);
	        g.setFont(new Font("Serif", Font.BOLD, 15));
	        g.drawString(answer1, ballX+10, ballY+20);

	        g.setColor(Color.red);
	        g.fillOval(ballX1, ballY1, UNIT_SIZE, UNIT_SIZE);

	        g.setColor(Color.WHITE);
	        g.setFont(new Font("Serif", Font.BOLD, 15));
	        g.drawString(answer2, ballX1+10, ballY1+20);

	        g.setColor(Color.red);
	        g.fillOval(ballX2, ballY2, UNIT_SIZE, UNIT_SIZE);

	        g.setColor(Color.WHITE);
	        g.setFont(new Font("Serif", Font.BOLD, 15));
	        g.drawString(answer3, ballX2+10, ballY2+20);
	        
		} else {
			gameEnd(g);
			timer.stop();
		}
	}
	
	
	private void gameEnd(Graphics g) {
	        g.setColor(Color.white);
	        g.setFont(new Font("Serif", Font.BOLD, 45));
	        g.drawString("Game over", 180, g.getFont().getSize()+30);

	        g.setColor(Color.green);
	        g.setFont(new Font("Serif", Font.BOLD, 35));
	        g.drawString("Score :" + marks, 220, SCREEN_HEIGHT / 3);

	        g.setColor(Color.white);
	        g.setFont(new Font("Serif", Font.BOLD, 20));
	        g.drawString("Press Enter to Retry..!", 200, 400);
	        
	        g.setColor(Color.white);
	        g.setFont(new Font("Serif", Font.BOLD, 20));
	        g.drawString("Press 'L' to View Score board..!", 200, 430);

	        actionBTN();
	}
	
	public void actionBTN() {
		retryBtnText.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            		Difficulty diff = new Difficulty();
                    gameFrame.dispose();
                    diff.setVisible(true);
            }
        });
	}

	
	private void checkRunning() {
		if(errorMarks > 0){
            running = false;
        } else if (second == 0) {
        	timer.stop();
        	running = false;
        }
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player1Up = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player1Down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player1Up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) { 
			player1Down = false;
			
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Bullet player1Bullet = new Bullet( 1 ,player1.getxPos()+20, player1.getyPos(), 20,20,"images/bullet.png");
			bullets.add(player1Bullet);
		} else if (!running && e.getKeyCode() == KeyEvent.VK_ENTER) {
			Difficulty difficulty_panel  = new Difficulty();
            gameFrame.dispose();
            difficulty_panel.setVisible(true);
		} else if (!running && e.getKeyCode() == KeyEvent.VK_L) {
			API api = API.getInstance();
			boolean saveScore = api.saveScore(marks);

			Dashboard dashboard = new Dashboard();
			dashboard.setVisible(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		checkRunning();
	}
	
	
}
