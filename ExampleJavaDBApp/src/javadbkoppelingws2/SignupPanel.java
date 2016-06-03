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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javadbkoppelingws2.Tab.conn;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Bas
 */
public class SignupPanel extends Tab {

    MyTableModel model;
    MyTableModel model2;
    String[][] dataValues = {};
    String[][] dataValues2 = {};
    String[] columnNames = {};
    String[] columnNames2 = {};
    JTable studentTable;
    JTable trajectTable;
    JScrollPane rightPanel;
    JScrollPane rightPanel2;

    JButton signupButton = new JButton("Student inschrijven");
    JButton trajectZoekenButton = new JButton("Zoeken");
    JButton studentZoekenButton = new JButton("Zoeken");
    
    JTextField trajectField = new JTextField();
    JTextField student_idField = new JTextField();
    JTextField studentVoornaamField= new JTextField();
    JTextField studentAchternaamField = new JTextField();
    JTextField trajectNaamField = new JTextField();

    JLabel trajectLabel = new JLabel("Traject:");
    JLabel studentLabel = new JLabel("Student ID:");
    JLabel studentVoornaamLabel = new JLabel("Voornaam:");
    JLabel studentAchternaamLabel = new JLabel("Achternaam:");
    JLabel trajectNaamLabel = new JLabel("Traject naam:");

    public SignupPanel(int width, int height) {
        super(width, height);

        addStudentTable();
        addTrajectTable();
        
        signupButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        signupButton.setLocation(X_MARGIN + 100, Y_MARGIN + 100);

        trajectField.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        trajectField.setLocation(X_MARGIN + 100, Y_MARGIN);

        student_idField.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        student_idField.setLocation(X_MARGIN + 100, Y_MARGIN + 50);

        trajectLabel.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        trajectLabel.setLocation(X_MARGIN, Y_MARGIN);

        studentLabel.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        studentLabel.setLocation(X_MARGIN, Y_MARGIN + 50);
        
        studentVoornaamLabel.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        studentVoornaamLabel.setLocation(X_MARGIN + 500, Y_MARGIN);
        
        studentAchternaamLabel.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        studentAchternaamLabel.setLocation(X_MARGIN + 500, Y_MARGIN + 50);
        
        studentZoekenButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        studentZoekenButton.setLocation(X_MARGIN + 600, Y_MARGIN + 100);
        
        studentVoornaamField.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        studentVoornaamField.setLocation(X_MARGIN + 600, Y_MARGIN);
        
        studentAchternaamField.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        studentAchternaamField.setLocation(X_MARGIN + 600, Y_MARGIN + 50);
        
        trajectNaamLabel.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        trajectNaamLabel.setLocation(X_MARGIN + 500, Y_MARGIN + 525);
        
        trajectNaamField.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        trajectNaamField.setLocation(X_MARGIN + 600, Y_MARGIN + 525);
        
        trajectZoekenButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        trajectZoekenButton.setLocation(X_MARGIN + 600, Y_MARGIN + 575);
        
        signupButton.addActionListener(new SignupPanel.ButtonListener(SignupPanel.ButtonAction.Inschrijven));

        add(signupButton);
        add(trajectField);
        add(trajectLabel);
        add(studentLabel);
        add(student_idField);
        add(studentVoornaamLabel);
        add(studentAchternaamLabel);
        add(studentAchternaamField);
        add(studentVoornaamField);
        add(trajectNaamField);
        add(trajectNaamLabel);
        add(trajectZoekenButton);
        add(studentZoekenButton);      
    }

    public ResultSet doQuery(String query) {
        Statement stat;
        ResultSet res = null;
        try {
            stat = conn.createStatement();

            res = stat.executeQuery(query);
            int counter = 0;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return res;
    }

    private void addTrajectTable() {

        String query = "SELECT Traject.traject_id, Traject.naam "
                + "FROM Traject;";

        ResultSet res = doQuery(query);

        columnNames2 = new String[2];
        columnNames2[0] = "traject_id";
        columnNames2[1] = "naam";

        int rowCount = 0;
        try {
            if (res.last()) {
                rowCount = res.getRow();
                res.beforeFirst();
            }
            dataValues2 = new String[rowCount][columnNames2.length];
            int counter = 0;
            while (res.next()) {
                dataValues2[counter][0] = res.getString("Traject.traject_id");
                dataValues2[counter][1] = res.getString("Traject.naam");
                counter++;
            }

            if (trajectTable == null || rightPanel2 == null) {
                trajectTable = new JTable(new MyTableModel(dataValues2, columnNames2));

                model2 = (MyTableModel) trajectTable.getModel();

                trajectTable.setShowVerticalLines(false);
                trajectTable.setRowSelectionAllowed(true);
                trajectTable.setColumnSelectionAllowed(false);

                rightPanel2 = new JScrollPane(trajectTable);
                rightPanel2.setSize(WIDTH / 2 - X_MARGIN * 2, HEIGHT - Y_MARGIN * 24 - COMPONENT_HEIGHT);
                rightPanel2.setLocation(WIDTH / 2 + X_MARGIN, Y_MARGIN + 525);
                add(rightPanel2);

            }
            model2.setColumnNames(columnNames2);
            model2.setNewData(dataValues2);
            repaint();
        } catch (SQLException e2) {
            //TODO
        }
    }

    private void addStudentTable() {

        String query = "SELECT Student.achternaam, Student.voornaam, Student.tussenvoegsel, Student.student_id "
                + "FROM Student "
                + "ORDER BY Student.achternaam;";

        ResultSet res = doQuery(query);

        columnNames = new String[4];
        columnNames[0] = "achternaam";
        columnNames[1] = "voornaam";
        columnNames[2] = "tussenvoegsel";
        columnNames[3] = "student_id";

        int rowCount = 0;
        try {
            if (res.last()) {
                rowCount = res.getRow();
                res.beforeFirst();
            }
            dataValues = new String[rowCount][columnNames.length];
            int counter = 0;
            while (res.next()) {
                dataValues[counter][0] = res.getString("Student.achternaam");
                dataValues[counter][1] = res.getString("Student.voornaam");
                dataValues[counter][2] = res.getString("Student.tussenvoegsel");
                dataValues[counter][3] = res.getString("Student.student_id");

                counter++;
            }

            if (studentTable == null || rightPanel == null) {
                studentTable = new JTable(new MyTableModel(dataValues, columnNames));

                model = (MyTableModel) studentTable.getModel();

                studentTable.setShowVerticalLines(false);
                studentTable.setRowSelectionAllowed(true);
                studentTable.setColumnSelectionAllowed(false);

                rightPanel = new JScrollPane(studentTable);
                rightPanel.setSize(WIDTH / 2 - X_MARGIN * 2, HEIGHT - Y_MARGIN * 20 - COMPONENT_HEIGHT);
                rightPanel.setLocation(WIDTH / 2 + X_MARGIN, Y_MARGIN);
                add(rightPanel);

            }
            model.setColumnNames(columnNames);
            model.setNewData(dataValues);
            repaint();
        } catch (SQLException e2) {
            //TODO
        }
    }

    private enum ButtonAction {
        Inschrijven, zoekTraject, zoekStudent
    }

    public class ButtonListener implements ActionListener {

        ButtonAction action;
        MyTableModel model;

        public ButtonListener(ButtonAction action) {
            this.action = action;
        }

        public String getDate() {
            Date date = new Date();
            String modifiedDate= new SimpleDateFormat("yyyy/MM/dd").format(date);
            
            /*    Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");

            String formatted = format1.format(cal.getTime());

            return formatted;
                */
            return modifiedDate;
        }

        public void errorBox(String infoMessage) {
            JOptionPane.showMessageDialog(null, infoMessage, "Error...", JOptionPane.ERROR_MESSAGE);
        }
        
        public void infoBox(String infoMessage) {
            JOptionPane.showMessageDialog(null, infoMessage, "Succes", JOptionPane.INFORMATION_MESSAGE);
        }

        public void actionPerformed(ActionEvent e) {
            if ((action == SignupPanel.ButtonAction.Inschrijven) && (!trajectField.getText().equals("")) && (!student_idField.getText().equals(""))) {
                Statement statement = null;

                String traject = trajectField.getText();
                String student_id = student_idField.getText();
                String date = getDate();
                System.out.println(date);
                String query = "insert into Student_Traject "
                        + "VALUES("
                        + student_id
                        + ", "
                        + traject
                        + ", 0, "
                        + date
                        + ");";

                try {
                    statement = DataSourceV2.getConnection().createStatement();
                    statement.executeUpdate(query);
                    infoBox("Student: " + student_id + " is toegevoegd aan traject: " + traject);
                } catch (SQLException ex) {
                    errorBox("mysql error");
                    Logger.getLogger(SignupPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                errorBox("Velden zijn niet ingevuld...");
            }
            
            if (action == ButtonAction.zoekStudent){
                
            }
        }

    }

}
