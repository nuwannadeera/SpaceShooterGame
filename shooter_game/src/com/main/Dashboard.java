package com.main;

import com.main.api.API;

import javax.swing.*;

import org.json.JSONObject;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Nuwan
 */
public class Dashboard extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JLabel lblPlayerLocation = new JLabel();
    private final JLabel lblHighScore = new JLabel();
    private final JLabel lblPlayer = new JLabel();
    private final JLabel lblYourScore = new JLabel();
    private final JPanel pnlDashboard = new JPanel();
    private final JTextField txtHighScore = new JTextField();
    private final JTextField txtPlayer = new JTextField();
    private final JTextField txtYourScore = new JTextField();
    private final JTextArea txtPlayerLocation = new JTextArea();
    
    public String IPAddress;
    public String location;

    /**
     * Creates new form NewJFrame
     */
    public Dashboard() {
        initComponents();

        setLocationRelativeTo(null);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        lblPlayerLocation.setText("Player Location");
        lblHighScore.setText("High Score");
        lblYourScore.setText("Your High Score");
        lblPlayer.setText("Players");

        txtPlayerLocation.setEnabled(false);
        txtPlayerLocation.setDisabledTextColor(Color.black);
        txtPlayer.setEnabled(false);
        txtPlayer.setDisabledTextColor(Color.black);
        txtHighScore.setEnabled(false);
        txtHighScore.setDisabledTextColor(Color.black);
        txtYourScore.setEnabled(false);
        txtYourScore.setDisabledTextColor(Color.black);
        getDashboardData();
        getPlayerIPAddress();
        getPlayerLocation();
    }

    private void initComponents() {

        GroupLayout pnlDashboardLayout = new GroupLayout(pnlDashboard);
        pnlDashboard.setLayout(pnlDashboardLayout);
        pnlDashboardLayout.setHorizontalGroup(
                pnlDashboardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlDashboardLayout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addGroup(pnlDashboardLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlDashboardLayout.createSequentialGroup()
                                                .addGroup(pnlDashboardLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(lblYourScore)
                                                        .addComponent(lblHighScore))
                                                .addGap(18, 18, 18)
                                                .addGroup(pnlDashboardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtHighScore, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtYourScore, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(pnlDashboardLayout.createSequentialGroup()
                                                .addComponent(lblPlayer)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtPlayer, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlDashboardLayout.createSequentialGroup()
                                                .addComponent(lblPlayerLocation)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtPlayerLocation, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(138, Short.MAX_VALUE))
        );
        pnlDashboardLayout.setVerticalGroup(
                pnlDashboardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pnlDashboardLayout.createSequentialGroup()
                                .addContainerGap(76, Short.MAX_VALUE)
                                .addGroup(pnlDashboardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblPlayer)
                                        .addComponent(txtPlayer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlDashboardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblHighScore)
                                        .addComponent(txtHighScore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlDashboardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblYourScore)
                                        .addComponent(txtYourScore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(68, 68, 68)
                                .addGroup(pnlDashboardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblPlayerLocation)
                                        .addComponent(txtPlayerLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlDashboard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlDashboard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();

    }
    
    private void getPlayerIPAddress() {
		URL url;
        try {
            url = new URL("https://api.ipify.org");
            InputStream inputStream = url.openStream();
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            String result2parse = result.toString(); 
            IPAddress = result2parse; 
            
        } catch (Exception e) {
            System.out.println("An Error occured: "+e.toString());
            e.printStackTrace();
        }
    }
    
    private void getPlayerLocation() {
    	URL url;
        try {
            url = new URL("http://ip-api.com/json/"+ IPAddress +"?fields=city,country");
            InputStream inputStream = url.openStream();
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            String result2parse = result.toString(); 

            String jsonString = result2parse; 
            JSONObject obj = new JSONObject(jsonString);
            String country = obj.getString("country");
            String city = obj.getString("city");
            location = city + " " + country;
            
            // set location
            txtPlayerLocation.setText(location);
            
        } catch (Exception e) {
            System.out.println("An Error occured: "+e.toString());
            e.printStackTrace();
        }
    }
    

    private void getDashboardData() {

        API api = API.getInstance();
        long highScore = api.getHighScore();
        System.out.println(highScore);
        txtHighScore.setText(highScore + "");

        long yourHighScore = api.getYourHighScore();
        txtYourScore.setText(yourHighScore + "");

        long playersCount = api.getPlayersCount();
        txtPlayer.setText(playersCount + "");

    }


}
