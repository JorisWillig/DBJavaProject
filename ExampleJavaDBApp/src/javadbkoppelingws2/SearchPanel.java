/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author joris
 */
public class SearchPanel extends Tab{
    
    JTextField firstNameBox = new JTextField();
    JTextField infixBox = new JTextField();
    JTextField surNameBox = new JTextField();
    JTextField emailBox = new JTextField();
    JTextField adresBox = new JTextField();
    JTextArea resultBox = new JTextArea();
    JButton searchButton = new JButton();
    
    private enum ButtonAction {
        Zoek
    }
    
    JTextField nameSearch;
    
    public SearchPanel(int width, int height) {
        super(width, height);
        fillLeft();
    }
    
    private void fillLeft() {
        firstNameBox.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        
        firstNameBox.setLocation(X_MARGIN, Y_MARGIN);
        
        
        
        resultBox.setSize(WIDTH/2-X_MARGIN*2, HEIGHT-Y_MARGIN*2-150);
        resultBox.setLocation(WIDTH/2+X_MARGIN, Y_MARGIN);

        add(firstNameBox);
        add(emailBox);
        add(resultBox);
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
