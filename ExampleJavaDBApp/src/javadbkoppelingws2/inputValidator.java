/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import java.util.regex.Pattern;

/**
 *
 * @author joris
 */
public class inputValidator {
    
    // Use Word for names, streets, countries, etc.
    public enum Field {
        Word, Email, Phone, HouseNumber, Street
    }
    
//    public boolean validate(String string, Field field) {
//        if(field == Field.Word)
//            return Pattern.matches("[A-Z][a-z]*", string);
//        if(field == Field.Email)
//            return Pattern.matches("^[a-zA-Z.$#!*&^%]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", string);
//        if(field == Field.Phone) {
//            
//        }
//    }
    
}
