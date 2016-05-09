/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
    
    JPanel panel = new JPanel();
    JTextField searchBox = new JTextField();
    JTextArea resultBox = new JTextArea();
    JButton nameSearchButton = new JButton();
    JButton townSearchButton = new JButton();
    
    static Connection conn;
    
    public App() {
        nameSearchButton.setSize(190, 30);
        nameSearchButton.setLocation(20,60);
        nameSearchButton.setText("Zoek op Naam");
        nameSearchButton.addActionListener(new zoekKnopListener(ButtonAction.NameSearch));
        townSearchButton.setSize(190, 30);
        townSearchButton.setLocation(230,60);
        townSearchButton.setText("Zoek op Stad");
        townSearchButton.addActionListener(new zoekKnopListener(ButtonAction.TownSearch));
        searchBox.setSize(400, 30);
        searchBox.setLocation(20, 20);
        resultBox.setSize(400, 300);
        resultBox.setLocation(20, 100);
        
        panel.setLayout(null);
        panel.add(searchBox);
        panel.add(resultBox);
        panel.add(nameSearchButton);
        panel.add(townSearchButton);
        add(panel);
    }
    
    public static void main(String[] args) {
        App app = new App();
        app.setVisible(true);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setSize(500,500);
        
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
            String zoekTekst = searchBox.getText();
            
            if(action == ButtonAction.NameSearch) {
                doQuery("SELECT * FROM customers WHERE customer_name LIKE '%" + zoekTekst + "%'");
            } else if(action == ButtonAction.TownSearch) {
                doQuery("SELECT * FROM customers WHERE customer_town LIKE '%" + zoekTekst + "%'");
            }
            
            
        }
    
    }
    
}
