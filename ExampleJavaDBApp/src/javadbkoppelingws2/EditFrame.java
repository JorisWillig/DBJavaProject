/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import static javadbkoppelingws2.Tab.conn;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author joris
 */
public class EditFrame extends JFrame {

    SearchPanel.ButtonListener parent;
    boolean isExchange;
    Map<String, JTextField> fields;
    int id;

    JPanel holdingPanel = new JPanel();

    JButton button;

    JFrame frame;
    
    ArrayList<JLabel> mandatoryLabels = new ArrayList<>();
    ArrayList<JTextField> mandatoryFields = new ArrayList<>();

    public EditFrame(SearchPanel.ButtonListener parent, boolean isExchange, Map<String, JTextField> fields, int id) {
        this.parent = parent;
        this.isExchange = isExchange;
        this.fields = fields;
        this.id = id;

        frame = this;

        setSize(700, 700);
        holdingPanel.setSize(700, 700);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        holdingPanel.setLayout(null);

        int compHeight = 30;
        int compWidth = 100;
        int xMargin = 20;
        int yMargin = 20;

        int counter = 0;
        for (Map.Entry<String, JTextField> pair : this.fields.entrySet()) {
            JLabel currentLabel = new JLabel(pair.getKey());
            currentLabel.setSize(compWidth, compHeight);
            pair.getValue().setSize(compWidth * 4, compHeight);
            currentLabel.setLocation(xMargin, yMargin * (counter + 1) + (compHeight * counter));
            pair.getValue().setLocation(xMargin * 2 + compWidth, yMargin * (counter + 1) + (compHeight * counter));
            holdingPanel.add(currentLabel);
            holdingPanel.add(pair.getValue());
            if(!(pair.getKey().equals("telnr_mob") || pair.getKey().equals("telnr_vast") || pair.getKey().equals("tussenvoegsel"))) {
                mandatoryLabels.add(currentLabel);
                mandatoryFields.add(pair.getValue());
            }
            counter++;
        }
        button = new JButton("Bewerken");
        button.setSize(compWidth, compHeight);
        button.setLocation(xMargin, 700 - yMargin * 5);
        button.addActionListener(new ButtonListener());
        holdingPanel.add(button);
        add(holdingPanel);
        setVisible(true);
    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (necessaryFieldsFilled()) {
                if (isExchange) {
                    String query = "UPDATE Student "
                            + "SET "
                            + "`voornaam`='" + fields.get("voornaam").getText() + "',"
                            + "`tussenvoegsel`='" + fields.get("tussenvoegsel").getText() + "',"
                            + "`achternaam`='" + fields.get("achternaam").getText() + "', "
                            + "`geslacht`='" + fields.get("geslacht").getText() + "', "
                            + "`email`='" + fields.get("email").getText() + "', "
                            + "`telnr_vast`='" + fields.get("telnr_vast").getText() + "', "
                            + "`telnr_mob`='" + fields.get("telnr_mob").getText() + "' "
                            + "WHERE `student_id` = " + id + ";";
                    parent.doUpdate(query);
                    query = "UPDATE Exchange_Student "
                            + "SET "
                            + "`huisnummer`='" + fields.get("huisnummer").getText() + "', "
                            + "`straat`='" + fields.get("straat").getText() + "', "
                            + "`woonplaats`='" + fields.get("woonplaats").getText() + "', "
                            + "`land`='" + fields.get("land").getText() + "' "
                            + "WHERE `student_id` = " + id + ";";
                    parent.doUpdate(query);

                } else {
                    String query = "UPDATE Student "
                            + "SET "
                            + "`voornaam`='" + fields.get("voornaam").getText() + "',"
                            + "`tussenvoegsel`='" + fields.get("tussenvoegsel").getText() + "',"
                            + "`achternaam`='" + fields.get("achternaam").getText() + "', "
                            + "`geslacht`='" + fields.get("geslacht").getText() + "', "
                            + "`email`='" + fields.get("email").getText() + "', "
                            + "`telnr_vast`='" + fields.get("telnr_vast").getText() + "', "
                            + "`telnr_mob`='" + fields.get("telnr_mob").getText() + "' "
                            + "WHERE `student_id` = " + id + ";";
                    parent.doUpdate(query);
                }
                JOptionPane.showMessageDialog(holdingPanel, "Student aangepast", "succes", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(holdingPanel, "Niet alle verplichten zijn ingevuld", "", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        private boolean necessaryFieldsFilled() {
            boolean succes = true;
            for(int i = 0; i < mandatoryFields.size(); i++) {
                JTextField field = mandatoryFields.get(i);
                if(field.getText().equals("")) {
                    succes = false;
                    mandatoryLabels.get(i).setForeground(Color.RED);
                }
            }
            return succes;
        }
    }
}
