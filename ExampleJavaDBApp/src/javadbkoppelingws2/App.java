/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbkoppelingws2;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author joris
 */
public class App extends JFrame {

    private enum ButtonAction {
        NameSearch, TownSearch;
    }

    int WIDTH;
    int HEIGHT;

    SearchPanel searchPanel;
    AddPanel addPanel;
    OverviewPanel overviewPanel;
    SignupPanel signupPanel;

    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel topPanel = new JPanel();

    public App() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);

        topPanel.setLayout(new BorderLayout());
        topPanel.add(tabbedPane);
        add(topPanel);

//        Initiating the Global variables
        WIDTH = getBounds().width;
        HEIGHT = getBounds().height;

        searchPanel = new SearchPanel(WIDTH, HEIGHT);
        addPanel = new AddPanel(WIDTH, HEIGHT);
        overviewPanel = new OverviewPanel(WIDTH, HEIGHT);
        signupPanel = new SignupPanel(WIDTH, HEIGHT);

        tabbedPane.addTab("Zoeken", searchPanel);
        tabbedPane.addTab("Toevoegen", addPanel);
        tabbedPane.addTab("Overzichten", overviewPanel);
        tabbedPane.addTab("Inschrijven", signupPanel);

    }

    private void fillSearchPane() {
        // Setting the size of the elements on the left side

    }

    public static void main(String[] args) {
        App app = new App();
    }

}