/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Stef
 */
public class OverviewPanel extends Tab {

    JButton countryButton = new JButton("Overzicht op land");
    JButton trajectButton = new JButton("Overzicht op traject");
    JButton schoolButton = new JButton("Overzicht op school");

    private enum ButtonAction {
        Overzict_op_land, Overzicht_op_traject, Overzicht_op_school
    }

    //todo gemiddelde aantal punten per traject van het maximale 
    public OverviewPanel(int width, int height) {
        super(width, height);
        fillLeft();
    }

    public void fillLeft() {
        countryButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        trajectButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        schoolButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        countryButton.setLocation(X_MARGIN, Y_MARGIN);
        trajectButton.setLocation(X_MARGIN, Y_MARGIN + 50);
        schoolButton.setLocation(X_MARGIN, Y_MARGIN + 100);

        countryButton.addActionListener(new OverviewPanel.ButtonListener(OverviewPanel.ButtonAction.Overzict_op_land));
        trajectButton.addActionListener(new OverviewPanel.ButtonListener(OverviewPanel.ButtonAction.Overzicht_op_traject));
        schoolButton.addActionListener(new OverviewPanel.ButtonListener(OverviewPanel.ButtonAction.Overzicht_op_school));

        add(countryButton);
        add(trajectButton);
        add(schoolButton);
    }

    public class ButtonListener implements ActionListener {

        ButtonAction action;
        MyTableModel model;

        public ButtonListener(ButtonAction action) {
            this.action = action;
        }

        public void actionPerformed(ActionEvent e) {
            if (action == ButtonAction.Overzict_op_land) {

            }
            if (action == ButtonAction.Overzicht_op_traject) {

            }
            if (action == ButtonAction.Overzicht_op_school) {

            }

        }

    }
}
