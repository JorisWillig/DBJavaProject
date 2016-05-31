/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static javadbkoppelingws2.Tab.conn;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
/**
 *
 * @author Bas
 */
public class SignupPanel extends Tab {
    
    JButton signupButton = new JButton("Student inschrijven");
    
    JTextField firstNameField = new JTextField();
    JTextField infixField = new JTextField();
    JTextField surNameField = new JTextField();  
    JTextField trajectField = new JTextField();
    
    JLabel firstNameLabel = new JLabel("Voornaam:");
    JLabel infixLabel = new JLabel("Tussenvoegsel(s):");
    JLabel surNameLabel = new JLabel("Achternaam:");
    JLabel trajectLabel = new JLabel("Traject:");
    
    public SignupPanel(int width, int height) {
        super(width, height);
        
        signupButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        signupButton.setLocation(X_MARGIN + 150, Y_MARGIN + 200);
        
        firstNameField.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        firstNameField.setLocation(X_MARGIN + 150, Y_MARGIN);
        
        infixField.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        infixField.setLocation(X_MARGIN + 150, Y_MARGIN + 50);
        
        surNameField.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        surNameField.setLocation(X_MARGIN + 150, Y_MARGIN + 100);
        
        trajectField.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        trajectField.setLocation(X_MARGIN + 150, Y_MARGIN + 150);
        
        firstNameLabel.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        firstNameLabel.setLocation(X_MARGIN, Y_MARGIN);
        
        infixLabel.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        infixLabel.setLocation(X_MARGIN, Y_MARGIN + 50);
        
        surNameLabel.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        surNameLabel.setLocation(X_MARGIN, Y_MARGIN + 100);
        
        trajectLabel.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        trajectLabel.setLocation(X_MARGIN, Y_MARGIN + 150);
        
        signupButton.addActionListener(new SignupPanel.ButtonListener(SignupPanel.ButtonAction.Inschrijven));
        
        add(firstNameField);
        add(infixField);
        add(surNameField);
        add(signupButton);
        add(trajectField);
        add(firstNameLabel);
        add(infixLabel);
        add(surNameLabel);
        add(trajectLabel);
    }
    
    private enum ButtonAction {
        Inschrijven
    }
    
    
    public class ButtonListener implements ActionListener {

        ButtonAction action;
        MyTableModel model;

        public ButtonListener(ButtonAction action) {
            this.action = action;
        }
      
    public void actionPerformed(ActionEvent e) {
            if (action == SignupPanel.ButtonAction.Inschrijven) {
                String firstName = firstNameField.getText();
                String infix = infixField.getText();
                String surName = surNameField.getText();
                String traject = trajectField.getText();
                
                String query = "SELECT Student.student_id, voornaam, "
                + "tussenvoegsel, achternaam, email, naam "
                + "FROM Student "
                + "LEFT JOIN Student_Traject "
                + "ON Student.student_id = Student_Traject.student_id "
                + "LEFT JOIN Traject "
                + "ON Student_Traject.traject_id = Traject.traject_id "
                + "WHERE voornaam LIKE '%"+firstName+"%' "
                + "AND tussenvoegsel LIKE '%"+infix+"%' "
                + "AND achternaam LIKE '%"+surName+"%' "
                + "AND COALESCE(naam, ' ') LIKE '%"+traject+"%'; ";
                
                
            }
    
        }
    
            public ResultSet doQuery(String query) {
            Statement stat;
            ResultSet res = null;
            try {
                stat = conn.createStatement();
                
                res = stat.executeQuery(query);
                int counter = 0;
            } catch(SQLException e1) {
                e1.printStackTrace();
            }
            return res;
        }
    }
}