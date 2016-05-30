package javadbkoppelingws2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class AddPanel extends Tab  {

    JButton addStudent = new JButton("Voeg een student toe");
    
    
    public AddPanel(int width, int height) {
        super(width, height);
        addComponents();
    }
    
    public void addComponents(){
        
        addStudent.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        addStudent.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                openStudentFrame();
            }
        });     
        add(addStudent);
        
    }
    
    public void openStudentFrame(){
        JFrame studentFrame = new JFrame();
        studentFrame.setVisible(true);
        studentFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        studentFrame.setSize(500, 500);
        
        JPanel studentPanel = new JPanel();
        studentFrame.add(studentPanel);
        
        JTextField voorNaamField = new JTextField();
        JTextField tussenVoegselField = new JTextField();
        JTextField achterNaamField = new JTextField();
        JTextField geslachtField = new JTextField();
        
        ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
        
        textFieldArray.add(voorNaamField);
        textFieldArray.add(tussenVoegselField);
        textFieldArray.add(achterNaamField);
        textFieldArray.add(geslachtField);
        
        
        int xmarg = 20;
        int ymarg = 15;
        
        for(JTextField i : textFieldArray){
            i.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
            i.setLocation(xmarg, ymarg);
            ymarg = ymarg + 5;
            studentPanel.add(i);
            repaint();
        }
        
                
                
        
    }
}
