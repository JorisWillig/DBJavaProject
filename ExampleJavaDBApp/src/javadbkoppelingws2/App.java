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
    
    JPanel topPanel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    
    JTabbedPane tabbedPane = new JTabbedPane();
    JTextField nameBox = new JTextField();
    JTextField townBox = new JTextField();
    JTextField emailBox = new JTextField();
    JTextArea resultBox = new JTextArea();
    JButton searchButton = new JButton();
    
    static Connection conn;
    
    public App() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        
        panel1.setLayout(null);
        
        tabbedPane.addTab("Search in students", panel1);
        tabbedPane.addTab("Overviews", panel2);
        tabbedPane.addTab("Add entity", panel3);
        
        topPanel.setLayout(new BorderLayout());
        topPanel.add(tabbedPane);
        add(topPanel);
        
        int width = getBounds().width;
        int height = getBounds().height;
        
        int X_MARGIN = width/40;
        int Y_MARGIN = height/40;
        int textFieldHeight = 30;
        
        searchButton.setSize(190, 30);
        searchButton.setLocation(20, 100);
        searchButton.setText("Zoek op Stad");
        searchButton.addActionListener(new zoekKnopListener(ButtonAction.TownSearch));
        nameBox.setSize(width/2 - X_MARGIN*2, textFieldHeight);
        townBox.setSize(width/2 - X_MARGIN*2, textFieldHeight);
        emailBox.setSize(width/2 - X_MARGIN*2, textFieldHeight);
        nameBox.setLocation(X_MARGIN, Y_MARGIN);
        townBox.setLocation(X_MARGIN, Y_MARGIN*2+textFieldHeight);
        emailBox.setLocation(X_MARGIN, Y_MARGIN*3+textFieldHeight*2);
        resultBox.setSize(width/2-X_MARGIN*2, height-Y_MARGIN*2-150);
        resultBox.setLocation(width/2+X_MARGIN, Y_MARGIN);
        
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();

        panel1.add(nameBox);
        panel1.add(townBox);
        panel1.add(emailBox);
        panel1.add(resultBox);
        panel1.add(searchButton);
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
                    resultBox.append(res.getString("customer_id") + " " +res.getString("customer_name") + " " + res.getString("customer_town") + "\n");
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
            String zoekTekst = nameBox.getText();
            
            if(action == ButtonAction.NameSearch) {
                doQuery("SELECT * FROM customers WHERE customer_name LIKE '%" + zoekTekst + "%'");
            } else if(action == ButtonAction.TownSearch) {
                doQuery("SELECT * FROM customers WHERE customer_town LIKE '%" + zoekTekst + "%'");
            }
            
            
        }
    
    }
    
}
