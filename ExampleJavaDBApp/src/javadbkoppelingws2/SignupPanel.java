/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
/**
 *
 * @author Bas
 */
public class SignupPanel extends Tab {
    
    JButton signupButton = new JButton("Inschrijven");
    JTextField studentField = new JTextField();
    JTextField trajectField = new JTextField();
    
    public SignupPanel(int width, int height) {
        super(width, height);
        signupButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        add(signupButton);
    }    
    
}
