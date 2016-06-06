/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author joris
 */
public class SearchPanel extends Tab {

    JLabel firstNameLabel = new JLabel("Voornaam:");
    JLabel infixLabel = new JLabel("Tussenvoegsel:");
    JLabel surNameLabel = new JLabel("Achternaam:");
    JLabel emailLabel = new JLabel("Emailadres:");
    JLabel trajectoryLabel = new JLabel("Traject:");
    JLabel fillerLabel = new JLabel("");
    JLabel cityLabel = new JLabel("Stad:");
    JLabel countryLabel = new JLabel("Land:");

    JTextField firstNameBox = new JTextField();
    JTextField infixBox = new JTextField();
    JTextField surNameBox = new JTextField();
    JTextField emailBox = new JTextField();
    JTextField trajectoryBox = new JTextField();
    JButton studentSearchButton = new JButton("Zoek in studenten");

    JTextField cityBox = new JTextField();
    JTextField countryBox = new JTextField();
    JButton locationSearchButton = new JButton("Zoek op locatie");

    JButton editButton = new JButton("Edit");
    JButton overViewButton = new JButton("Punten overzicht");

    ButtonAction currentAction;

    ArrayList<JLabel> labels = new ArrayList<>();
    ArrayList<JComponent> textFields = new ArrayList<>();

    JScrollPane rightPanel;
    String[][] dataValues = {};
    String[] columnNames = {};
    JTable table;

    MyTableModel studentModel;
    MyTableModel locationModel;
    MyTableModel overViewModel;

    private enum ButtonAction {
        ZoekStudenten, ZoekLocatie, Edit, ScoreOverview
    }

    public SearchPanel(int width, int height) {
        super(width, height);
        fillArrays();
        fillLeft();
        fillRight();

    }

    private void fillArrays() {
        labels.add(firstNameLabel);
        labels.add(infixLabel);
        labels.add(surNameLabel);
        labels.add(emailLabel);
        labels.add(trajectoryLabel);
        labels.add(fillerLabel);
        labels.add(cityLabel);
        labels.add(countryLabel);

        textFields.add(firstNameBox);
        textFields.add(infixBox);
        textFields.add(surNameBox);
        textFields.add(emailBox);
        textFields.add(trajectoryBox);
        textFields.add(studentSearchButton);
        textFields.add(cityBox);
        textFields.add(countryBox);
        textFields.add(locationSearchButton);
    }

    private void fillLeft() {
        int lowerLine;
        for (int i = 0; i < labels.size(); i++) {
            labels.get(i).setSize(COMPONENT_WIDTH / 3 * 2, COMPONENT_HEIGHT);
            labels.get(i).setLocation(X_MARGIN, Y_MARGIN * (i + 1) + COMPONENT_HEIGHT * i);
            add(labels.get(i));
        }

        for (int i = 0; i < textFields.size(); i++) {
            textFields.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            textFields.get(i).setLocation(X_MARGIN * 2 + COMPONENT_WIDTH / 3 * 2, Y_MARGIN * (i + 1) + COMPONENT_HEIGHT * i);
            add(textFields.get(i));
        }

        studentSearchButton.addActionListener(new ButtonListener(ButtonAction.ZoekStudenten));
        locationSearchButton.addActionListener(new ButtonListener(ButtonAction.ZoekLocatie));
    }

    private void fillRight() {
        table = new JTable();
        table.setShowVerticalLines(false);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        rightPanel = new JScrollPane(table);
        rightPanel.setSize(WIDTH / 2 - X_MARGIN * 2, HEIGHT - Y_MARGIN * 6 - COMPONENT_HEIGHT);
        rightPanel.setLocation(WIDTH / 2 + X_MARGIN, Y_MARGIN);
        add(rightPanel);

        editButton.setSize((WIDTH / 2 - X_MARGIN * 2) / 2 - X_MARGIN / 2, COMPONENT_HEIGHT);
        editButton.setLocation(WIDTH / 2 + X_MARGIN, rightPanel.getY() + rightPanel.getHeight() + Y_MARGIN);
        editButton.addActionListener(new ButtonListener(ButtonAction.Edit));
        add(editButton);
        editButton.setEnabled(false);

        overViewButton.setSize((WIDTH / 2 - X_MARGIN * 2) / 2 - X_MARGIN / 2, COMPONENT_HEIGHT);
        overViewButton.setLocation(editButton.getX() + editButton.getWidth() + X_MARGIN, rightPanel.getY() + rightPanel.getHeight() + Y_MARGIN);
        overViewButton.addActionListener(new ButtonListener(ButtonAction.ScoreOverview));
        add(overViewButton);
        overViewButton.setEnabled(false);
    }

    public class ButtonListener implements ActionListener {

        ButtonAction action;

        public ButtonListener(ButtonAction action) {
            this.action = action;
        }

        public void actionPerformed(ActionEvent e) {
            if (action == ButtonAction.ZoekStudenten) {
                String fN = firstNameBox.getText();
                String iF = infixBox.getText();
                String sN = surNameBox.getText();
                String em = emailBox.getText();
                String traj = trajectoryBox.getText();

                String query = "SELECT Student.student_id, voornaam, "
                        + "tussenvoegsel, achternaam, email, naam "
                        + "FROM Student "
                        + "LEFT JOIN Student_Traject "
                        + "ON Student.student_id = Student_Traject.student_id "
                        + "LEFT JOIN Traject "
                        + "ON Student_Traject.traject_id = Traject.traject_id "
                        + "WHERE voornaam LIKE '%" + fN + "%' "
                        + "AND COALESCE(tussenvoegsel, ' ') LIKE '%" + iF + "%' "
                        + "AND achternaam LIKE '%" + sN + "%' "
                        + "AND email LIKE '%" + em + "%' "
                        + "AND COALESCE(naam, ' ') LIKE '%" + traj + "%'; ";
                ResultSet res = doQuery(query);

                columnNames = new String[6];
                columnNames[0] = "ID";
                columnNames[1] = "Voornaam";
                columnNames[2] = "Tussenvoegsel";
                columnNames[3] = "Achternaam";
                columnNames[4] = "Emailadres";
                columnNames[5] = "Traject";

                int rowCount = 0;
                try {
                    if (res.last()) {
                        rowCount = res.getRow();
                        res.beforeFirst();
                    }
                    dataValues = new String[rowCount][columnNames.length];
                    int counter = 0;
                    while (res.next()) {
                        dataValues[counter][0] = res.getString("student_id");
                        dataValues[counter][1] = res.getString("voornaam");
                        dataValues[counter][2] = res.getString("tussenvoegsel");
                        dataValues[counter][3] = res.getString("achternaam");
                        dataValues[counter][4] = res.getString("email");
                        dataValues[counter][5] = res.getString("naam");
                        counter++;
                    }

                    if (studentModel == null) {
                        studentModel = new MyTableModel(dataValues, columnNames);
                    }

                    editButton.setEnabled(true);
                    overViewButton.setEnabled(true);
                    table.setModel(studentModel);

                    studentModel.setColumnNames(columnNames);
                    studentModel.setNewData(dataValues);
                    repaint();
                } catch (SQLException e2) {
                    //TODO
                }
            } else if (action == ButtonAction.Edit) {
                if (table.getSelectedRow() >= 0) {
                    int row = table.getSelectedRow();
                    Object[] rowData = studentModel.getRowData(row);
                    ResultSet res = doQuery("SELECT student_id FROM Exchange_Student");
                    boolean isExchange = false;
                    try {
                        while (res.next()) {
                            if (rowData[0].toString().equals(res.getString("student_id"))) {
                                isExchange = true;
                                System.out.println("is exchange");
                            }
                        }
                        LinkedHashMap<String, JTextField> fields;
                        if (isExchange) {
                            res = doQuery("SELECT voornaam, tussenvoegsel, achternaam, geslacht, email, telnr_vast, telnr_mob, huisnummer, straat, woonplaats, land "
                                    + "FROM Student INNER JOIN Exchange_Student ON "
                                    + "Student.student_id = Exchange_Student.student_id "
                                    + "WHERE Student.student_id = '" + rowData[0] + "'");
                        } else {
                            res = doQuery("SELECT voornaam, tussenvoegsel, achternaam, geslacht, email, telnr_vast, telnr_mob "
                                    + "FROM Student "
                                    + "INNER JOIN HHS_Student ON "
                                    + "Student.student_id = HHS_Student.student_id "
                                    + "WHERE Student.student_id = '" + rowData[0] + "'");
                        }
                        res.first();
                        fields = fillMap(res);

                        EditFrame editFrame = new EditFrame(this, isExchange, fields, Integer.parseInt((String) rowData[0]));

                    } catch (SQLException editEx) {

                    }
                } else {
                    JOptionPane.showMessageDialog(rightPanel, "Selecteer eerst een rij die u wilt bewerken", "error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (action == ButtonAction.ScoreOverview) {
                if (table.getSelectedRow() >= 0) {
                    int row = table.getSelectedRow();
                    Object[] rowData = studentModel.getRowData(row);
                    ResultSet res;
                    String query = "SELECT Student.student_id, naam, behaalde_punten "
                            + "FROM Student "
                            + "INNER JOIN Student_Traject "
                            + "ON Student.student_id = Student_Traject.student_id "
                            + "LEFT JOIN Traject "
                            + "ON Student_Traject.traject_id = Traject.traject_id "
                            + "WHERE Student.student_id = " + rowData[0] ;
                    res = doQuery(query);
                    columnNames = new String[3];
                    columnNames[0] = "Student ID";
                    columnNames[1] = "Traject";
                    columnNames[2] = "Behaalde punten";

                    int rowCount = 0;
                    try {
                        if (res.last()) {
                            rowCount = res.getRow();
                            res.beforeFirst();
                        }
                        dataValues = new String[rowCount][columnNames.length];
                        int counter = 0;
                        while (res.next()) {
                            dataValues[counter][0] = res.getString("student_id");
                            dataValues[counter][1] = res.getString("naam");
                            dataValues[counter][2] = res.getString("behaalde_punten");
                            counter++;
                        }

                        if (overViewModel == null) {
                            overViewModel = new MyTableModel(dataValues, columnNames);
                        }

                        editButton.setEnabled(true);
                        overViewButton.setEnabled(true);
                        table.setModel(overViewModel);

                        overViewModel.setColumnNames(columnNames);
                        overViewModel.setNewData(dataValues);
                        repaint();
                    } catch (SQLException e2) {
                        //TODO
                    }

                } else {
                    JOptionPane.showMessageDialog(rightPanel, "Selecteer eerst een student die u wilt bekijken", "error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (action == ButtonAction.ZoekLocatie) {
                String stad = cityBox.getText();
                String land = countryBox.getText();

                String query = "SELECT stad, land, voornaam, tussenvoegsel, achternaam, email, telnr_mob "
                        + "FROM School "
                        + "LEFT JOIN Opleiding "
                        + "ON Opleiding.school_id = School.school_id "
                        + "LEFT JOIN Traject "
                        + "ON Traject.opleiding_id = Opleiding.opleiding_id "
                        + "LEFT JOIN Student_Traject "
                        + "ON Traject.traject_id = Student_Traject.traject_id "
                        + "LEFT JOIN Student "
                        + "ON Student_Traject.student_id = Student.student_id "
                        + "WHERE Student.student_id IN (SELECT student_id FROM HHS_Student)"
                        + "AND stad LIKE '%" + stad + "%'"
                        + "AND land LIKE '%" + land + "%'";
                ResultSet res = doQuery(query);

                columnNames = new String[7];
                columnNames[0] = "Stad";
                columnNames[1] = "Land";
                columnNames[2] = "Voornaam";
                columnNames[3] = "Tussenvoegsel";
                columnNames[4] = "Achternaam";
                columnNames[5] = "Emailadres";
                columnNames[6] = "Mobiel nummer";

                int rowCount = 0;
                try {
                    if (res.last()) {
                        rowCount = res.getRow();
                        res.beforeFirst();
                    }
                    dataValues = new String[rowCount][columnNames.length];
                    int counter = 0;
                    while (res.next()) {
                        dataValues[counter][0] = res.getString("stad");
                        dataValues[counter][1] = res.getString("land");
                        dataValues[counter][2] = res.getString("voornaam");
                        dataValues[counter][3] = res.getString("tussenvoegsel");
                        dataValues[counter][4] = res.getString("achternaam");
                        dataValues[counter][5] = res.getString("email");
                        dataValues[counter][6] = res.getString("telnr_mob");
                        counter++;
                    }

                    editButton.setEnabled(false);

                    if (locationModel == null) {
                        locationModel = new MyTableModel(dataValues, columnNames);
                    }

                    table.setModel(locationModel);

                    locationModel.setColumnNames(columnNames);
                    locationModel.setNewData(dataValues);
                    repaint();
                } catch (SQLException e2) {
                    //TODO
                }
            }
        }

        private LinkedHashMap<String, JTextField> fillMap(ResultSet res) {
            LinkedHashMap<String, JTextField> map = new LinkedHashMap<String, JTextField>();
            try {
                ResultSetMetaData metaData = res.getMetaData();
                int colCount = metaData.getColumnCount();
                for (int i = 0; i < colCount; i++) {
                    String colName = metaData.getColumnName(i + 1);
                    map.put(colName, new JTextField(res.getString(i + 1)));
                }
            } catch (SQLException mapEx) {
                //TODO
            }
            return map;
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

        public void doUpdate(String query) {

            Statement stat;
            try {
                stat = conn.createStatement();

                stat.executeUpdate(query);
                int counter = 0;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
