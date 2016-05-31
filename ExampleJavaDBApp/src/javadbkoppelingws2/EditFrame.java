/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import java.util.Map;
import javax.swing.JFrame;

/**
 *
 * @author joris
 */
public class EditFrame extends JFrame{
    
    boolean isExchange;
    Map fields;
    
    public EditFrame(boolean isExchange, Map fields) {
        this.isExchange = isExchange;
        this.fields = fields;
    }
    
    
    
}
