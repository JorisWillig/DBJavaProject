/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author joris
 */
public class SearchPanel extends Tab{
    
    JLabel firstNameLabel = new JLabel("Voornaam:");
    JLabel infixLabel = new JLabel("Tussenvoegsel:");
    JLabel surNameLabel = new JLabel("Achternaam:");
    JLabel emailLabel = new JLabel("Emailadres:");
    JLabel addressLabel = new JLabel("Adres:");
    JLabel trajectoryLabel = new JLabel("Traject:");
    
    JTextField firstNameBox = new JTextField();
    JTextField infixBox = new JTextField();
    JTextField surNameBox = new JTextField();
    JTextField emailBox = new JTextField();
    JTextField addressBox = new JTextField();
    JTextField trajectoryBox = new JTextField();
    JButton searchButton = new JButton("Zoek");
    
    
    ArrayList labels = new ArrayList<JLabel>();
    ArrayList textFields = new ArrayList<JTextField>();
    
    private enum ButtonAction {
        Zoek
    }
    
    JTextField nameSearch;
    
    public SearchPanel(int width, int height) {
        super(width, height);
        //fillArrays();
        fillLeft();
    }
    
    private void fillLeft() {
//        for(JLabel label : labels) {
//            
//        }
        
        firstNameLabel.setSize(TEXT_FIELD_WIDTH/3*2, TEXT_FIELD_HEIGHT);
        infixLabel.setSize(TEXT_FIELD_WIDTH/3*2, TEXT_FIELD_HEIGHT);
        surNameLabel.setSize(TEXT_FIELD_WIDTH/3*2, TEXT_FIELD_HEIGHT);
        firstNameLabel.setSize(TEXT_FIELD_WIDTH/3*2, TEXT_FIELD_HEIGHT);
        firstNameLabel.setSize(TEXT_FIELD_WIDTH/3*2, TEXT_FIELD_HEIGHT);
        firstNameLabel.setSize(TEXT_FIELD_WIDTH/3*2, TEXT_FIELD_HEIGHT);
        
        firstNameBox.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        infixBox.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        surNameBox.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        firstNameBox.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        firstNameBox.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        firstNameBox.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        
        
        firstNameBox.setLocation(X_MARGIN, Y_MARGIN);

        add(firstNameLabel);
        add(infixLabel);
        add(surNameLabel);
        add(emailLabel);
        add(addressLabel);
        add(trajectoryLabel);
        add(searchButton);
        
        add(firstNameBox);
        add(infixBox);
        add(surNameBox);
        add(emailBox);
        add(addressBox);
        add(trajectoryBox);
        add(searchButton);
    }
    
    public class ButtonListener implements ActionListener {
        
        ButtonAction action;
        
        public ButtonListener(ButtonAction action) {
            this.action = action;
        }
        
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
