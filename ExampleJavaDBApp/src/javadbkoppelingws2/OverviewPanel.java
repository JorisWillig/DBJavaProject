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
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Stef
 */
public class OverviewPanel extends Tab {

    JButton countryExchangeButton = new JButton("Overzicht op land (exchange)");
    JButton trajectButton = new JButton("Overzicht op traject");
    JButton schoolButton = new JButton("Overzicht op school");
    JButton countryHHSButton = new JButton("Overzicht op land (hhs)");

    JTable table;
    MyTableModel model;
    String[][] dataValues = {};
    String[] columnNames = {};
    JScrollPane rightPanel;

    MyTableModel landExchangeModel;
    MyTableModel landHHSModel;
    MyTableModel trajectModel;
    MyTableModel schoolModel;

    private enum ButtonAction {
        Overzicht_op_land_exchangeStudent, Overzicht_op_traject, Overzicht_op_school, Overzicht_op_land_HHS_Student
    }

    //todo gemiddelde aantal punten per traject van het maximale 
    public OverviewPanel(int width, int height) {
        super(width, height);
        fillLeft();
        fillRight();
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

    }

    public void fillLeft() {
        countryExchangeButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        countryHHSButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        trajectButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        schoolButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);

        countryExchangeButton.setLocation(X_MARGIN, Y_MARGIN);
        countryHHSButton.setLocation(X_MARGIN, Y_MARGIN * 2 + COMPONENT_HEIGHT);
        trajectButton.setLocation(X_MARGIN, Y_MARGIN * 3 + COMPONENT_HEIGHT * 2);
        schoolButton.setLocation(X_MARGIN, Y_MARGIN * 4 + COMPONENT_HEIGHT * 3);

        countryExchangeButton.addActionListener(new OverviewPanel.ButtonListener(OverviewPanel.ButtonAction.Overzicht_op_land_exchangeStudent));
        countryHHSButton.addActionListener(new OverviewPanel.ButtonListener(OverviewPanel.ButtonAction.Overzicht_op_land_HHS_Student));
        trajectButton.addActionListener(new OverviewPanel.ButtonListener(OverviewPanel.ButtonAction.Overzicht_op_traject));
        schoolButton.addActionListener(new OverviewPanel.ButtonListener(OverviewPanel.ButtonAction.Overzicht_op_school));

        add(countryExchangeButton);
        add(countryHHSButton);
        add(trajectButton);
        add(schoolButton);
    }

    public class ButtonListener implements ActionListener {

        ButtonAction action;

        public ButtonListener(ButtonAction action) {
            this.action = action;
        }

        public void actionPerformed(ActionEvent e) {
            if (action == ButtonAction.Overzicht_op_land_exchangeStudent) {
                String query = "SELECT School.land "
                        + ", count(Exchange_Student.student_id) as Aantal_studenten "
                        + "FROM School "
                        + "INNER JOIN Exchange_Student ON School.school_id = Exchange_Student.school_id "
                        + "GROUP BY School.land ORDER BY Aantal_studenten DESC;";
                ResultSet res = doQuery(query);
                columnNames = new String[2];
                columnNames[0] = "Land";
                columnNames[1] = "Aantal studenten";

                int rowCount = 0;
                try {
                    if (res.last()) {
                        rowCount = res.getRow();
                        res.beforeFirst();
                    }
                    dataValues = new String[rowCount][columnNames.length];
                    int counter = 0;
                    while (res.next()) {
                        dataValues[counter][0] = res.getString("School.land");
                        dataValues[counter][1] = res.getString("Aantal_studenten");
                        counter++;
                    }

                    if (landExchangeModel == null) {
                        landExchangeModel = new MyTableModel(dataValues, columnNames);
                    }

                    table.setModel(landExchangeModel);

                    landExchangeModel.setColumnNames(columnNames);
                    landExchangeModel.setNewData(dataValues);
                    repaint();
                } catch (SQLException e2) {
                    //TODO
                }
            }

            if (action == ButtonAction.Overzicht_op_land_HHS_Student) {
                String query = "SELECT  School.land, count(HHS_Student.student_id) as Aantal_studenten "
                        + "FROM HHS_Student "
                        + "INNER JOIN Student_Traject "
                        + "ON HHS_Student.student_id = Student_Traject.student_id "
                        + "LEFT JOIN Traject "
                        + "ON Student_Traject.traject_id = Traject.traject_id "
                        + "LEFT JOIN Opleiding "
                        + "ON Traject.opleiding_id = Opleiding.opleiding_id "
                        + "LEFT JOIN School "
                        + "ON Opleiding.school_id = School.school_id "
                        + "GROUP BY School.land "
                        + "ORDER BY Aantal_studenten DESC;";
                ResultSet res = doQuery(query);
                columnNames = new String[2];
                columnNames[0] = "Land";
                columnNames[1] = "Aantal studenten";

                int rowCount = 0;
                try {
                    if (res.last()) {
                        rowCount = res.getRow();
                        res.beforeFirst();
                    }
                    dataValues = new String[rowCount][columnNames.length];
                    int counter = 0;
                    while (res.next()) {
                        dataValues[counter][0] = res.getString("School.land");
                        dataValues[counter][1] = res.getString("Aantal_studenten");
                        counter++;
                    }

                    if (landHHSModel == null) {
                        landHHSModel = new MyTableModel(dataValues, columnNames);
                    }

                    table.setModel(landHHSModel);

                    landHHSModel.setColumnNames(columnNames);
                    landHHSModel.setNewData(dataValues);
                    repaint();
                } catch (SQLException e2) {
                    //TODO
                }
            }
            if (action == ButtonAction.Overzicht_op_traject) {
                String query = " SELECT Student_Traject.traject_id, Traject.naam"
                        + ", count(Student_Traject.Student_id) as Aantal_studenten "
                        + "FROM Student_Traject "
                        + "LEFT JOIN Traject ON Student_Traject.traject_id = Traject.traject_id "
                        + "GROUP BY Student_Traject.traject_id ORDER BY Aantal_studenten DESC;";

                ResultSet res = doQuery(query);

                columnNames = new String[3];
                columnNames[0] = "Traject ID";
                columnNames[1] = "Traject naam";
                columnNames[2] = "Aantal studenten";

                int rowCount = 0;
                try {
                    if (res.last()) {
                        rowCount = res.getRow();
                        res.beforeFirst();
                    }
                    dataValues = new String[rowCount][columnNames.length];
                    int counter = 0;
                    while (res.next()) {
                        dataValues[counter][0] = res.getString("Student_Traject.traject_id");
                        dataValues[counter][1] = res.getString("Traject.naam");
                        dataValues[counter][2] = res.getString("Aantal_studenten");
                        counter++;
                    }

                    if (trajectModel == null) {
                        trajectModel = new MyTableModel(dataValues, columnNames);
                    }

                    table.setModel(trajectModel);

                    trajectModel.setColumnNames(columnNames);
                    trajectModel.setNewData(dataValues);
                    repaint();
                } catch (SQLException e2) {
                    //TODO
                }
            }

            if (action == ButtonAction.Overzicht_op_school) {

                String query = "select School.school_id, School.schoolnaam"
                        + ", count(Exchange_Student.student_id) as Aantal_studenten "
                        + "FROM School "
                        + "LEFT JOIN Exchange_Student ON Exchange_Student.school_id = School.school_id "
                        + "GROUP BY School.school_id ORDER BY Aantal_studenten DESC;";
                ResultSet res = doQuery(query);

                columnNames = new String[3];
                columnNames[0] = "School ID";
                columnNames[1] = "School naam";
                columnNames[2] = "Aantal studenten";
                int rowCount = 0;
                try {
                    if (res.last()) {
                        rowCount = res.getRow();
                        res.beforeFirst();
                    }
                    dataValues = new String[rowCount][columnNames.length];
                    int counter = 0;
                    while (res.next()) {
                        dataValues[counter][0] = res.getString("School.school_id");
                        dataValues[counter][1] = res.getString("School.schoolnaam");
                        dataValues[counter][2] = res.getString("Aantal_studenten");
                        counter++;
                    }
                    if (schoolModel == null) {
                        schoolModel = new MyTableModel(dataValues, columnNames);
                    }

                    table.setModel(schoolModel);

                    schoolModel.setColumnNames(columnNames);
                    schoolModel.setNewData(dataValues);
                    repaint();
                } catch (SQLException e2) {
                    //TODO
                }
            }
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
    }
}
