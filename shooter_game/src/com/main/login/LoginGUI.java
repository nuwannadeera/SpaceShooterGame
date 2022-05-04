package com.main.login;

import javax.swing.*;
import javax.swing.border.Border;

import com.main.Difficulty;
import com.main.signup.RegisterGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

public class LoginGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6921462126880570161L;

	JButton blogin = new JButton("Login");
	JPanel panel = new JPanel();
	JTextField txuser = new JTextField(30);
	JPasswordField pass = new JPasswordField(30);
	JLabel label1 = new JLabel("Login");
	JLabel label2 = new JLabel("User Name");
	JLabel label3 = new JLabel("Password");
	JLabel label4 = new JLabel("Register Now");
	JButton regbtn = new JButton("Register");
	
	Login ldata = new Login();
	int wrongLoginCount = 0;

	public LoginGUI() {
		setSize(400, 300);
		setLocation(500, 280);
		panel.setLayout(null);
		Image icon = Toolkit.getDefaultToolkit().getImage("images/rocket.png");  
		this.setIconImage(icon);  
		
		label1.setFont(new Font("Serif", Font.BOLD, 28));
		label1.setBounds(155, 15, 170, 33);
		label1.setBackground(new Color(100, 20, 70));
		label2.setBounds(160, 55, 170, 28);
		txuser.setBounds(90, 80, 200, 30);
		label3.setBounds(160, 105, 220, 28);
		pass.setBounds(90, 135, 200, 30);
		blogin.setBounds(90, 180, 200, 30);
		regbtn.setBounds(90, 220, 100, 20);
		blogin.setBackground(Color.BLACK);
		blogin.setForeground(Color.WHITE);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		regbtn.setBorder(emptyBorder);
		regbtn.setBackground(Color.WHITE);

		panel.add(blogin);
		panel.add(txuser);
		panel.add(pass);
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(regbtn);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionBTN();
	}


	public void actionBTN() {
		regbtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	RegisterGUI reg = new RegisterGUI(); 
            	reg.setVisible(true);
                dispose();
            }
        });
		
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String puname = txuser.getText();
				String ppaswd = String.valueOf(pass.getPassword());
				if( ldata.checkPassword(puname, ppaswd)) {
					Difficulty gme = new Difficulty(); 
					gme.setVisible(true); 
					dispose();
				} else {
						JOptionPane.showMessageDialog(null, "Wrong Password / Username");
						txuser.setText("");
						pass.setText("");
						txuser.requestFocus();
						if (wrongLoginCount > 2) {
							System.exit(0);
						}
						wrongLoginCount++;
				}
			}
        });
	}
	
	
	public static void main(String[] args) {
		new LoginGUI(); 
	}
}
