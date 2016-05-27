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
    
    SearchPanel searchPanel;
//    AddPanel addPanel;
    OverviewPanel overviewPanel;
//    SignupPanel signupPanel;
    
    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel topPanel = new JPanel();
        
    public App() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        
        topPanel.setLayout(new BorderLayout());
        topPanel.add(tabbedPane);
        add(topPanel);
        
        //Initiating the Global variables
        WIDTH = getBounds().width;
        HEIGHT = getBounds().height;
        
        searchPanel = new SearchPanel(WIDTH, HEIGHT);
//        addPanel = new AddPanel(WIDTH, HEIGHT);
        overviewPanel = new OverviewPanel(WIDTH, HEIGHT);
//        signupPanel = new SignupPanel(WIDTH, HEIGHT);
        
        tabbedPane.addTab("Zoeken", searchPanel);
        tabbedPane.addTab("Toevoegen", addPanel);
        tabbedPane.addTab("Overzichten", overviewPanel);
        tabbedPane.addTab("Inschrijven", signupPanel);
        
    }
    
    private void fillSearchPane() {
         // Setting the size of the elements on the left side

    }
    
    public static void main(String[] args) {
        App app = new App();
    }
    
//    public void doQuery(String query) {
//        Statement stat;
//            ResultSet res;
//            try {
//                stat = conn.createStatement();
//                
//                res = stat.executeQuery(query);
//                int counter = 0;
//                resultBox.setText(null);
//                while(res.next()) {
//                    resultBox.append(res.getString("customer_id") + " " +res.getString("first_name") + " " + res.getString("address") + "\n");
//                    counter++;
//                }
//                if(counter == 0) {
//                    resultBox.append("No results");
//                }
//            } catch(SQLException e1) {
//                e1.printStackTrace();
//            }
//    }
    
//    public class zoekKnopListener implements ActionListener {
//        
//        ButtonAction action;
//        
//        public zoekKnopListener(ButtonAction action) {
//            this.action = action;
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String firstNameText = nameBox.getText();
//            
//            doQuery("SELECT * FROM customers WHERE first_name LIKE '%" + firstNameText + "%'");
//        }
//    }
}
