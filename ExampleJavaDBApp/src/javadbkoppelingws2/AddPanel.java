package javadbkoppelingws2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class AddPanel extends Tab {

    JButton addStudent = new JButton("Voeg een HHS-student toe");

    public AddPanel(int width, int height) {
        super(width, height);
        addComponents();
    }

    public void addComponents() {

        addStudent.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        addStudent.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                openStudentFrame();
            }
        });
        add(addStudent);

    }

    public void openStudentFrame() {
        JFrame studentFrame = new JFrame();
        studentFrame.setVisible(true);
        studentFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        studentFrame.setSize(700, 400);

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(null);
        studentFrame.add(studentPanel);

        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Voeg toe");

        JTextField voornaamField = new JTextField();
        JTextField tussenvoegselField = new JTextField();
        JTextField achternaamField = new JTextField();
        JTextField geslachtField = new JTextField();
        JTextField opleidingField = new JTextField();

        JLabel voornaamLabel = new JLabel("Voornaam: ");
        JLabel tussenvoegselLabel = new JLabel("Tussenvoegsel: ");
        JLabel achternaamLabel = new JLabel("Achternaam: ");
        JLabel geslachtLabel = new JLabel("Geslacht: ");
        JLabel opleidingLabel = new JLabel("Opleiding: ");

        ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
        ArrayList<JLabel> labelArray = new ArrayList<JLabel>();

        //Add all textfields to an array
        textFieldArray.add(voornaamField);
        textFieldArray.add(tussenvoegselField);
        textFieldArray.add(achternaamField);
        textFieldArray.add(geslachtField);
        textFieldArray.add(opleidingField);       

        //Add all labels to an array
        labelArray.add(voornaamLabel);
        labelArray.add(tussenvoegselLabel);
        labelArray.add(achternaamLabel);
        labelArray.add(geslachtLabel);
        labelArray.add(opleidingLabel);

        int xmarg = 180;
        int ymarg = 15;

        for (int i = 0; i < textFieldArray.size(); i++) {
            textFieldArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            textFieldArray.get(i).setLocation(xmarg, ymarg + i * 45);
            labelArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            labelArray.get(i).setLocation(textFieldArray.get(i).getX() - 155, textFieldArray.get(i).getY());

            studentPanel.add(textFieldArray.get(i));
            studentPanel.add(labelArray.get(i));
        }

        cancelButton.setSize(90, 30);
        cancelButton.setLocation(20, ymarg + (textFieldArray.size() * 45));
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                studentFrame.dispose();
            }
        });

        /*
         INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country)
         VALUES ('Cardinal','Tom B. Erichsen','Skagen 21','Stavanger','4006','Norway'); 
         */
        addButton.setSize(90, 30);
        addButton.setLocation(COMPONENT_WIDTH, ymarg + (textFieldArray.size() * 45));
        addButton.addActionListener(new ActionListener() {

//            String values = "" + "'" + voornaamField.getText() + "', " + "'" + tussenvoegselField.getText() + "', " + "'" + achternaamField.getText() + "', " + "'" + geslachtField.getText() + "')"); 
            public void actionPerformed(ActionEvent e) {
                Statement statement = null;
                ResultSet resultset = null;
                int oldRowCount = getRows("Student");
                String opleidingText = opleidingField.getText();

                if (voornaamField.getText().isEmpty()
                        || achternaamField.getText().isEmpty()
                        || geslachtField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(studentFrame, "U hebt niet alles ingevuld. ");
                } else if(!geslachtField.getText().equals("Male") || !geslachtField.getText().equals("Female") ){
                    JOptionPane.showMessageDialog(studentFrame, "U moet 'Male' voor man of 'Female' voor vrouw invullen bij geslacht. ");
                }else {
                    try {
                        statement = DataSourceV2.getConnection().createStatement();
                        statement.executeUpdate("INSERT INTO Student (voornaam, tussenvoegsel, achternaam, geslacht)" + createValuesQuery(textFieldArray));

                    } catch (SQLException ex) {
                        Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(createValuesQuery(textFieldArray));
                }
                
                if(getRows("Student") == oldRowCount + 1){
                    JOptionPane.showMessageDialog(studentFrame, "Student is toegevoegd!");
                }
            }
        });

        studentPanel.add(cancelButton);
        studentPanel.add(addButton);
    }

    //Creates a query with values based on the amount of textfields | TFA stands for TextFieldArray
    public String createValuesQuery(ArrayList<JTextField> TFA) {
        StringBuilder sb = new StringBuilder();
        for (JTextField i : TFA) {
            if (i == TFA.get(0)) {
                sb.append("VALUES ( '" + i.getText().replaceAll("\\s+", "") + "', ");
            } else if (i == TFA.get(TFA.size() - 1)) {
                sb.append("'" + i.getText().replaceAll("\\s+", "") + "')");
            } else {
                sb.append("'" + i.getText().replaceAll("\\s+", "") + "', ");
            }
        }
        return sb.toString();
    }

    public int getRows(String table) {
        int rowCount = 0;
                
        try {
            Statement statement = DataSourceV2.getConnection().createStatement();
            ResultSet resultset = statement.executeQuery("SELECT COUNT(*) FROM Student");
            resultset.next();
            rowCount = resultset.getInt(1);
            System.out.println(resultset.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }
    
    public boolean checkOpleiding(String opleiding){
        try {
            Statement statement = DataSourceV2.getConnection().createStatement();
            ResultSet resultset = statement.executeQuery("SELECT * FROM Opleiding WHERE opleiding_naam = " + opleiding);
            resultset.next();
            //checken of er een row is?
        } catch (SQLException ex) {
            Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
