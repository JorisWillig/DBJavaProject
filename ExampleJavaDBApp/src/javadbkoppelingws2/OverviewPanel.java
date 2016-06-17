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
import java.util.ArrayList;
import static javadbkoppelingws2.Tab.conn;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Stef
 */
public final class OverviewPanel extends Tab {

    JButton countryExchangeButton = new JButton("Overzicht op land (exchange)");
    JButton trajectButton = new JButton("Overzicht op traject");
    JButton schoolButton = new JButton("Overzicht op school");
    JButton countryHHSButton = new JButton("Overzicht op land (hhs)");
    JButton populariteitSoortButton = new JButton("Overzicht populariteit op onderwijssoort");

    JLabel opleidingLabel = new JLabel("Overzicht van Studenten per opleiding");

    ArrayList<String> opleidingen = new ArrayList<>();
    JComboBox<String> opleidingBox = new JComboBox();

    JTable table;
    MyTableModel model;
    String[][] dataValues = {};
    String[] columnNames = {};
    JScrollPane rightPanel;

    MyTableModel landExchangeModel;
    MyTableModel landHHSModel;
    MyTableModel trajectModel;
    MyTableModel schoolModel;
    MyTableModel populariteitModel;
    MyTableModel opleidingModel;

    private enum ButtonAction {
        Overzicht_op_land_exchangeStudent, Overzicht_op_traject, Overzicht_op_school, Overzicht_op_land_HHS_Student,
        Overzicht_op_populariteitSoort
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

    private void fillComboBox() throws SQLException {
        ArrayList<String> alleOpleidingen = new ArrayList<>();

        String query = "SELECT Opleiding.opleiding_naam FROM Opleiding ORDER BY Opleiding.opleiding_naam ";
        try (ResultSet res = doQuery(query)) {
            while (res.next()) {
                String opleiding = res.getString("Opleiding.opleiding_naam");
                alleOpleidingen.add(opleiding);
            }
            opleidingBox.setModel(new DefaultComboBoxModel(alleOpleidingen.toArray()));
        }
    }

//todo gemiddelde aantal punten per traject van het maximale 
    public OverviewPanel(int width, int height) throws SQLException {
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

    public void fillLeft() throws SQLException {
        countryExchangeButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        countryHHSButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        trajectButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        schoolButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        populariteitSoortButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        opleidingBox.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        opleidingLabel.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);

        opleidingBox.setLocation(X_MARGIN, Y_MARGIN * 6 + COMPONENT_HEIGHT * 5);
        countryExchangeButton.setLocation(X_MARGIN, Y_MARGIN);
        countryHHSButton.setLocation(X_MARGIN, Y_MARGIN * 2 + COMPONENT_HEIGHT);
        trajectButton.setLocation(X_MARGIN, Y_MARGIN * 3 + COMPONENT_HEIGHT * 2);
        schoolButton.setLocation(X_MARGIN, Y_MARGIN * 4 + COMPONENT_HEIGHT * 3);
        populariteitSoortButton.setLocation(X_MARGIN, Y_MARGIN * 5 + COMPONENT_HEIGHT * 4);
        opleidingLabel.setLocation(X_MARGIN * 2 + COMPONENT_WIDTH, Y_MARGIN * 6 + COMPONENT_HEIGHT * 5);

        countryExchangeButton.addActionListener(new OverviewPanel.ButtonListener(OverviewPanel.ButtonAction.Overzicht_op_land_exchangeStudent));
        countryHHSButton.addActionListener(new OverviewPanel.ButtonListener(OverviewPanel.ButtonAction.Overzicht_op_land_HHS_Student));
        trajectButton.addActionListener(new OverviewPanel.ButtonListener(OverviewPanel.ButtonAction.Overzicht_op_traject));
        schoolButton.addActionListener(new OverviewPanel.ButtonListener(OverviewPanel.ButtonAction.Overzicht_op_school));
        populariteitSoortButton.addActionListener(new OverviewPanel.ButtonListener(OverviewPanel.ButtonAction.Overzicht_op_populariteitSoort));
        opleidingBox.addActionListener(new OverviewPanel.MyActionListener());

        add(countryExchangeButton);
        add(countryHHSButton);
        add(trajectButton);
        add(schoolButton);
        add(populariteitSoortButton);
        add(opleidingBox);
        add(opleidingLabel);
        fillComboBox();
    }

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            //opleidingBox = (JComboBox) evt.getSource();
            String x = "'" + opleidingBox.getSelectedItem().toString() + "'";
            String query = "SELECT Student.voornaam, Student.tussenvoegsel, Student.achternaam, Exchange_Student.land as land_van_herkomst "
                    + "FROM Exchange_Student "
                    + "JOIN School "
                    + "ON Exchange_Student.school_id = School.school_id "
                    + "JOIN Opleiding "
                    + "ON School.school_id = Opleiding.school_id "
                    + "JOIN Student "
                    + "ON Exchange_Student.student_id = Student.student_id "
                    + "WHERE Opleiding.opleiding_naam = "
                    + x
                    + ";";

            ResultSet res = doQuery(query);

            columnNames = new String[4];
            columnNames[0] = "Voornaam";
            columnNames[1] = "Tussenvoegsel";
            columnNames[2] = "Achternaam";
            columnNames[3] = "Land van herkomst";

            int rowCount = 0;
            try {
                if (res.last()) {
                    rowCount = res.getRow();
                    res.beforeFirst();
                }
                dataValues = new String[rowCount][columnNames.length];
                int counter = 0;
                while (res.next()) {
                    dataValues[counter][0] = res.getString("Student.voornaam");
                    dataValues[counter][1] = res.getString("Student.tussenvoegsel");
                    dataValues[counter][2] = res.getString("Student.achternaam");
                    dataValues[counter][3] = res.getString("land_van_herkomst");

                    counter++;
                }

                if (opleidingModel == null) {
                    opleidingModel = new MyTableModel(dataValues, columnNames);
                }

                table.setModel(opleidingModel);

                opleidingModel.setColumnNames(columnNames);
                opleidingModel.setNewData(dataValues);
                repaint();
            } catch (SQLException e2) {

            }
        }
    }

    public class ButtonListener implements ActionListener {

        ButtonAction action;

        public ButtonListener(ButtonAction action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (action == ButtonAction.Overzicht_op_populariteitSoort) {
                String query = "SELECT Traject.jaar, Studie.soort, count(Student_Traject.student_id) as aantal_studenten "
                        + "FROM Student_Traject "
                        + "INNER JOIN Traject "
                        + "ON Student_Traject.traject_id = Traject.traject_id "
                        + "INNER JOIN Studie "
                        + "ON Traject.traject_id = Studie.traject_id "
                        + "GROUP BY Traject.jaar, Studie.soort "
                        + "UNION "
                        + "SELECT jaar, 'Stage' as soort, count(student_id) as aantal_studenten "
                        + "FROM Student_Traject "
                        + "INNER JOIN Traject "
                        + "ON Student_Traject.traject_id = Traject.traject_id "
                        + "INNER JOIN Stage "
                        + "ON Traject.traject_id = Stage.traject_id "
                        + "GROUP BY jaar, soort "
                        + "ORDER BY jaar, soort;";
                ResultSet res = doQuery(query);
                columnNames = new String[3];
                columnNames[0] = "Schooljaar";
                columnNames[1] = "OnderwijsSoort";
                columnNames[2] = "Aantal_studenten";
                int rowCount = 0;
                try {
                    if (res.last()) {
                        rowCount = res.getRow();
                        res.beforeFirst();
                    }
                    dataValues = new String[rowCount][columnNames.length];
                    int counter = 0;
                    while (res.next()) {
                        dataValues[counter][0] = res.getString("jaar");
                        dataValues[counter][1] = res.getString("soort");
                        dataValues[counter][2] = res.getString("aantal_studenten");
                        counter++;
                    }

                    System.out.println(populariteitModel);
                    if (populariteitModel == null) {
                        populariteitModel = new MyTableModel(dataValues, columnNames);
                    }

                    table.setModel(populariteitModel);

                    populariteitModel.setColumnNames(columnNames);
                    populariteitModel.setNewData(dataValues);
                    repaint();
                } catch (SQLException e2) {
                    //TODO
                }
            }

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
                if (table.getSelectedRow() >= 0) {
                    System.out.println("1");
                    int row = table.getSelectedRow();
                    Object land = table.getValueAt(1, row);
                    land.toString();
                    System.out.println(land);

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
    }
}
