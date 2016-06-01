/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import java.util.ArrayList;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author joris
 */
public class EditFrame extends JFrame{
    
    boolean isExchange;
    Map<String, String> fields;
    
    public EditFrame(SearchPanel.ButtonListener parent, boolean isExchange, Map<String, String> fields) {
        this.isExchange = isExchange;
        this.fields = fields;
        
        setSize(700, 700);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        ArrayList<JTextField> fieldList = new ArrayList<>();
        
        int compHeight = 30;
        int compWidth = 100;
        int xMargin = 20;
        int yMargin = 20;
        
        int counter = 0;
        for(Map.Entry<String, String> pair: this.fields.entrySet()) {
            JLabel currentLabel = new JLabel(pair.getKey());
            JTextField currentField = new JTextField(pair.getValue());
            currentLabel.setSize(compWidth, compHeight);
            currentField.setSize(compWidth, compHeight);
            currentLabel.setLocation(xMargin, yMargin*(counter+1) + (compHeight*counter));
            currentField.setLocation(xMargin*2+compWidth, yMargin*(counter+1) + (compHeight*counter));
            add(currentLabel);
            add(currentField);
            counter++;
        }
    }
    
        
}
