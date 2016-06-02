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
import static javadbkoppelingws2.Tab.conn;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Bas
 */
public class SignupPanel extends Tab {

    MyTableModel model;

    String[][] dataValues = {};
    String[] columnNames = {};
    JTable table;
    JScrollPane rightPanel;

    JButton signupButton = new JButton("Student inschrijven");

    JTextField trajectField = new JTextField();
    JTextField student_idField = new JTextField();

    JLabel trajectLabel = new JLabel("Traject:");
    JLabel studentLabel = new JLabel("Student ID:");

    public SignupPanel(int width, int height) {
        super(width, height);

        signupButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        signupButton.setLocation(X_MARGIN + 150, Y_MARGIN + 200);

        trajectField.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        trajectField.setLocation(X_MARGIN + 150, Y_MARGIN);

        student_idField.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        student_idField.setLocation(X_MARGIN + 150, Y_MARGIN + 100);

        trajectLabel.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        trajectLabel.setLocation(X_MARGIN, Y_MARGIN);

        studentLabel.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        studentLabel.setLocation(X_MARGIN, Y_MARGIN + 100);

        signupButton.addActionListener(new SignupPanel.ButtonListener(SignupPanel.ButtonAction.Inschrijven));

        add(signupButton);
        add(trajectField);
        add(trajectLabel);
        add(studentLabel);
        add(student_idField);

        addStudentTable();
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

            if (table == null || rightPanel == null) {
                table = new JTable(new MyTableModel(dataValues, columnNames));

                model = (MyTableModel) table.getModel();

                table.setShowVerticalLines(false);
                table.setRowSelectionAllowed(true);
                table.setColumnSelectionAllowed(false);

                rightPanel = new JScrollPane(table);
                rightPanel.setSize(WIDTH / 2 - X_MARGIN * 2, HEIGHT - Y_MARGIN * 6 - COMPONENT_HEIGHT);
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

        Inschrijven
    }

    public class ButtonListener implements ActionListener {

        ButtonAction action;
        MyTableModel model;

        public ButtonListener(ButtonAction action) {
            this.action = action;
        }

        public void actionPerformed(ActionEvent e) {
            if (action == SignupPanel.ButtonAction.Inschrijven) {
                String traject = trajectField.getText();

                String query = "SELECT Student.student_id, Traject.traject_id, "
                        + "FROM Student "
                        + "LEFT JOIN Student_Traject "
                        + "ON Student.student_id = Student_Traject.student_id "
                        + "LEFT JOIN Traject "
                        + "ON Student_Traject.traject_id = Traject.traject_id "
                        + "AND COALESCE(naam, ' ') LIKE '%" + traject + "%'; ";

            }

        }

    }

}
