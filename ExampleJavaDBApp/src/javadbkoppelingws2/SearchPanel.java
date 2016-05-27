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
        fillArrays();
        fillLeft();
    }
    
    private void fillArrays() {
        labels.add(firstNameLabel);
        labels.add(infixLabel);
        labels.add(surNameLabel);
        labels.add(emailLabel);
        labels.add(addressLabel);
        labels.add(trajectoryLabel);
        
        textFields.add(firstNameBox);
        textFields.add(infixBox);
        textFields.add(surNameBox);
        textFields.add(emailBox);
        textFields.add(addressBox);
        textFields.add(trajectoryBox);
    }
    
    private void fillLeft() {
        for(JLabel label : labels) {
            label.setSize(COMPONENT_WIDTH/3*2, COMPONENT_HEIGHT);
        }
        
        firstNameLabel.setSize(COMPONENT_WIDTH/3*2, COMPONENT_HEIGHT);
        infixLabel.setSize(COMPONENT_WIDTH/3*2, COMPONENT_HEIGHT);
        surNameLabel.setSize(COMPONENT_WIDTH/3*2, COMPONENT_HEIGHT);
        firstNameLabel.setSize(COMPONENT_WIDTH/3*2, COMPONENT_HEIGHT);
        firstNameLabel.setSize(COMPONENT_WIDTH/3*2, COMPONENT_HEIGHT);
        firstNameLabel.setSize(COMPONENT_WIDTH/3*2, COMPONENT_HEIGHT);
        
        firstNameBox.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        infixBox.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        surNameBox.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        firstNameBox.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        firstNameBox.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        firstNameBox.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        
        
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
