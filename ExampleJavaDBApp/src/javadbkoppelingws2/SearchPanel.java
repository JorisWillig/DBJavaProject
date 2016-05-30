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
import javax.swing.JButton;
import javax.swing.JLabel;
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
public class SearchPanel extends Tab{
    
    JLabel firstNameLabel = new JLabel("Voornaam:");
    JLabel infixLabel = new JLabel("Tussenvoegsel:");
    JLabel surNameLabel = new JLabel("Achternaam:");
    JLabel emailLabel = new JLabel("Emailadres:");
    JLabel trajectoryLabel = new JLabel("Traject:");
    
    JTextField firstNameBox = new JTextField();
    JTextField infixBox = new JTextField();
    JTextField surNameBox = new JTextField();
    JTextField emailBox = new JTextField();
    JTextField trajectoryBox = new JTextField();
    JButton searchButton = new JButton("Zoek");
    
    ArrayList<JLabel> labels = new ArrayList<>();
    ArrayList<JTextField> textFields = new ArrayList<>();
    
    JScrollPane rightPanel;
    String[][] dataValues = {};
    String[] columnNames = {};
    JTable table;
    
    private enum ButtonAction {
        Zoek
    }
    
    JTextField nameSearch;
    
    public SearchPanel(int width, int height) {
        super(width, height);
        fillArrays();
        fillLeft();        
    }
    
    private void fillArrays() {
        labels.add(firstNameLabel);
        labels.add(infixLabel);
        labels.add(surNameLabel);
        labels.add(emailLabel);
        labels.add(trajectoryLabel);
        
        textFields.add(firstNameBox);
        textFields.add(infixBox);
        textFields.add(surNameBox);
        textFields.add(emailBox);
        textFields.add(trajectoryBox);
    }
    
    private void fillLeft() {
        int lowerLine;
        for(int i = 0; i < labels.size(); i++) {
            labels.get(i).setSize(COMPONENT_WIDTH/3*2, COMPONENT_HEIGHT);
            labels.get(i).setLocation(X_MARGIN, Y_MARGIN*(i+1)+COMPONENT_HEIGHT*i);
            add(labels.get(i));
        }
        
        for(int i = 0; i < textFields.size(); i++) {
            textFields.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            textFields.get(i).setLocation(X_MARGIN*2+COMPONENT_WIDTH/3*2, Y_MARGIN*(i+1)+COMPONENT_HEIGHT*i);
            add(textFields.get(i));
        }
        
        searchButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        searchButton.setLocation(textFields.get(textFields.size()-1).getX(), textFields.get(textFields.size()-1).getY()+COMPONENT_HEIGHT+Y_MARGIN);
        searchButton.addActionListener(new ButtonListener(ButtonAction.Zoek));
        add(searchButton);
    }
    
    public class ButtonListener implements ActionListener {
        
        ButtonAction action;
        
        public ButtonListener(ButtonAction action) {
            this.action = action;
        }
        
        public void actionPerformed(ActionEvent e) {
            if(action == ButtonAction.Zoek) {
                String fN = firstNameBox.getText();
                String iF = infixBox.getText();
                String sN = surNameBox.getText();
                String em = emailBox.getText();
                String traj = trajectoryBox.getText();
                
                String query = "SELECT Student.student_id, voornaam, "
                        + "tussenvoegsel, achternaam, email, naam "
                        + "FROM Student "
                        + "LEFT JOIN Student_Email "
                        + "ON Student.student_id = Student_Email.student_id "
                        + "LEFT JOIN Student_Traject "
                        + "ON Student.student_id = Student_Traject.student_id "
                        + "LEFT JOIN Traject "
                        + "ON Student_Traject.traject_id = Traject.traject_id "
                        + "WHERE voornaam LIKE '%"+fN+"%' "
                        + "AND tussenvoegsel LIKE '%"+iF+"%' "
                        + "AND achternaam LIKE '%"+sN+"%' "
                        + "AND COALESCE(email, ' ') LIKE '%"+em+"%' "
                        + "AND COALESCE(naam, ' ') LIKE '%"+traj+"%'; ";
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
                    if(res.last()) {
                        rowCount = res.getRow();
                        res.beforeFirst();
                    }
                    dataValues = new String[rowCount][6];
                    int counter = 0;
                    while(res.next()) {
                        dataValues[counter][0] = res.getString("student_id");
                        dataValues[counter][1] = res.getString("voornaam");
                        dataValues[counter][2] = res.getString("tussenvoegsel");
                        dataValues[counter][3] = res.getString("achternaam");
                        dataValues[counter][4] = res.getString("email");
                        dataValues[counter][5] = res.getString("naam");
                        counter++;
                    }
                    if(table == null || rightPanel == null) {
                        table = new JTable(new MyTableModel(dataValues, columnNames));
                    
                        table.setShowVerticalLines(false);
                        table.setRowSelectionAllowed(true);
                        table.setColumnSelectionAllowed(false);
                        
                        rightPanel = new JScrollPane(table);
                        rightPanel.setSize(WIDTH/2-X_MARGIN*2, HEIGHT - Y_MARGIN*6 - COMPONENT_HEIGHT);
                        rightPanel.setLocation(WIDTH/2-X_MARGIN, Y_MARGIN);
                        add(rightPanel);
                    }
                    MyTableModel model = (MyTableModel)table.getModel();
                    model.setColumnNames(columnNames);
                    model.setNewData(dataValues);
                    repaint();
                } catch(SQLException e2) {
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
            } catch(SQLException e1) {
                e1.printStackTrace();
            }
            return res;
        }
    }
    
    public class MyTableModel extends AbstractTableModel {

        Object[][] data;
        String[] columns;
        
        public MyTableModel(Object[][] data, String[] columns) {
            this.data = data;
            this.columns = columns;
        }
        
        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }
        
        public void setNewData(Object[][] newData) {
            this.data = newData;
            fireTableDataChanged();
        }
        
        public void setColumnNames(String[] newColumns) {
            this.columns = columns;
            fireTableStructureChanged();
        }
        
        public Object[] getRowData(int index) {
            return data[index];
        }
        
    }
}
