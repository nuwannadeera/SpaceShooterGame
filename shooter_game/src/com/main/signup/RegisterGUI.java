package com.main.signup;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.main.login.LoginGUI;


public class RegisterGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton bsignup = new JButton("Sign Up");
	JPanel panel = new JPanel();
	JTextField txuser = new JTextField(30);
	JPasswordField pass = new JPasswordField(30);
	JPasswordField conpass = new JPasswordField(30);
	JLabel label1 = new JLabel("Signup");
	JLabel label2 = new JLabel("User Name");
	JLabel label3 = new JLabel("Password");
	JLabel label4 = new JLabel("Confirm Password");
	
	Register ldata = new Register(); 

	public RegisterGUI() {
		setSize(400, 500);
		setLocation(500, 180);
		panel.setLayout(null);
		Image icon = Toolkit.getDefaultToolkit().getImage("images/rocket.png");  
		this.setIconImage(icon);  
		
		label1.setFont(new Font("Serif", Font.BOLD, 28));
		label1.setBounds(150, 15, 170, 33);
		label1.setBackground(new Color(100, 20, 70));
		label2.setBounds(160, 55, 170, 28);
		txuser.setBounds(90, 80, 200, 30);
		label3.setBounds(160, 105, 220, 28);
		pass.setBounds(90, 135, 200, 30);
		
		label4.setBounds(135, 165, 220, 28);
		conpass.setBounds(90, 195, 200, 30);
		
		bsignup.setBounds(90, 270, 200, 30);
		bsignup.setBackground(Color.BLACK);
		bsignup.setForeground(Color.WHITE);


		panel.add(bsignup);
		panel.add(txuser);
		panel.add(pass);
		panel.add(conpass);
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		actionRegister();
	}
	
	public void actionRegister() {
		bsignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String puname = txuser.getText();
				String ppaswd = String.valueOf(pass.getPassword());
				String conpaswd = String.valueOf(conpass.getPassword());
				if( ldata.check(puname, ppaswd,conpaswd)) {
					JOptionPane.showMessageDialog(null, "Registration Success");
					LoginGUI login_pane = new LoginGUI(); 
					login_pane.setVisible(true); 
					dispose();
				} else {
					if (puname.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Username is empty !");
					} else if (ppaswd.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Password is empty !");
					} else if (conpaswd.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Confirm Password is empty !");
					} else if (!ppaswd.equals(conpaswd)) {
						JOptionPane.showMessageDialog(null, "Password does not match !");
					} else {
						JOptionPane.showMessageDialog(null, "Username already Exist !");
					}
					txuser.setText("");
					pass.setText("");
					conpass.setText("");
					txuser.requestFocus();
				}
			}
		});
	}

}
