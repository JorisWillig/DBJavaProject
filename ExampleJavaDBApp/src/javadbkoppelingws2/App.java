/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author joris
 */
public class App extends JFrame{
    
    private enum ButtonAction {
        NameSearch, TownSearch;
    }
    
    int WIDTH;
    int HEIGHT;
    
    int X_MARGIN;
    int Y_MARGIN;
    int TEXT_FIELD_HEIGHT;
    int TEXT_FIELD_WIDTH;
    
    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel topPanel = new JPanel();
    JPanel searchPanel = new JPanel();
    JPanel overviewPanel = new JPanel();
    JPanel editPanel = new JPanel();
    
    // Content of searchPanel
    JTextField nameBox = new JTextField();
    JTextField townBox = new JTextField();
    JTextField emailBox = new JTextField();
    JTextArea resultBox = new JTextArea();
    JButton searchButton = new JButton();
    
    // Content of overviewPanel
    
    // Content of editPanel
    
    static Connection conn;
    
    public App() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        
        searchPanel.setLayout(null);
        
        tabbedPane.addTab("Search in students", searchPanel);
        tabbedPane.addTab("Overviews", overviewPanel);
        tabbedPane.addTab("Add entity", editPanel);
        
        topPanel.setLayout(new BorderLayout());
        topPanel.add(tabbedPane);
        add(topPanel);
        
        //Initiating the Global variables
        WIDTH = getBounds().width;
        HEIGHT = getBounds().height;
        
        X_MARGIN = WIDTH/40;
        Y_MARGIN = HEIGHT/40;
        TEXT_FIELD_HEIGHT = 30;
        TEXT_FIELD_WIDTH = 300;
        
        fillSearchPane();
    }
    
    private void fillSearchPane() {
         // Setting the size of the elements on the left side
        nameBox.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        townBox.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        emailBox.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        searchButton.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        
        // Setting the location of the elements on the left side
        nameBox.setLocation(X_MARGIN, Y_MARGIN);
        townBox.setLocation(X_MARGIN, Y_MARGIN*2+TEXT_FIELD_HEIGHT);
        emailBox.setLocation(X_MARGIN, Y_MARGIN*3+TEXT_FIELD_HEIGHT*2);
        searchButton.setLocation(X_MARGIN, Y_MARGIN*4+TEXT_FIELD_HEIGHT*3);
        searchButton.setText("Zoek");
        searchButton.addActionListener(new zoekKnopListener(ButtonAction.TownSearch));
        
        
        resultBox.setSize(WIDTH/2-X_MARGIN*2, HEIGHT-Y_MARGIN*2-150);
        resultBox.setLocation(WIDTH/2+X_MARGIN, Y_MARGIN);
        
        
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();

        searchPanel.add(nameBox);
        searchPanel.add(townBox);
        searchPanel.add(emailBox);
        searchPanel.add(resultBox);
        searchPanel.add(searchButton);
    }
    
    public static void main(String[] args) {
        App app = new App();
        
        try {
            conn = new DataSourceV2().getConnection();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void doQuery(String query) {
        Statement stat;
            ResultSet res;
            try {
                stat = conn.createStatement();
                
                res = stat.executeQuery(query);
                int counter = 0;
                resultBox.setText(null);
                while(res.next()) {
                    resultBox.append(res.getString("customer_id") + " " +res.getString("first_name") + " " + res.getString("address") + "\n");
                    counter++;
                }
                if(counter == 0) {
                    resultBox.append("No results");
                }
            } catch(SQLException e1) {
                e1.printStackTrace();
            }
    }
    
    public class zoekKnopListener implements ActionListener {
        
        ButtonAction action;
        
        public zoekKnopListener(ButtonAction action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String firstNameText = nameBox.getText();
            
            doQuery("SELECT * FROM customers WHERE first_name LIKE '%" + firstNameText + "%'");
        }
    }
}
