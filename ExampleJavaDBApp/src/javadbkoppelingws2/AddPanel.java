package javadbkoppelingws2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class AddPanel extends Tab {

    JButton addHHSStudentButton = new JButton("Voeg een HHS-student toe");
    JButton addExchangeStudentButton = new JButton("Voeg een Exchange-student toe");

    public AddPanel(int width, int height) {
        super(width, height);
        addComponents();
    }

    public void addComponents() {

        addHHSStudentButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        addHHSStudentButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                openHHSStudentFrame();
            }
        });

        addExchangeStudentButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        addExchangeStudentButton.setLocation(addHHSStudentButton.getX() + COMPONENT_WIDTH, addHHSStudentButton.getY());
        addExchangeStudentButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                openExchangeStudentFrame();
            }
        });

        add(addHHSStudentButton);
        add(addExchangeStudentButton);

    }

    public void openHHSStudentFrame() {
        JFrame studentFrame = new JFrame();
        studentFrame.setVisible(true);
        studentFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        studentFrame.setSize(700, 500);

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(null);
        studentFrame.add(studentPanel);

        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Voeg toe");

        JTextField voornaamField = new JTextField();
        JTextField tussenvoegselField = new JTextField();
        JTextField achternaamField = new JTextField();
        JTextField geslachtField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField telvastField = new JTextField();
        JTextField telmobField = new JTextField();
        JTextField opleidingField = new JTextField();

        JLabel voornaamLabel = new JLabel("Voornaam: ");
        JLabel tussenvoegselLabel = new JLabel("Tussenvoegsel: ");
        JLabel achternaamLabel = new JLabel("Achternaam: ");
        JLabel geslachtLabel = new JLabel("Geslacht: ");
        JLabel emailLabel = new JLabel("Email: ");
        JLabel telvastLabel = new JLabel("Telefoonnummer: ");
        JLabel telmobLabel = new JLabel("Mobiele nummer: ");
        JLabel opleidingLabel = new JLabel("Opleiding ID: ");

        ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
        ArrayList<JTextField> textForStudentArray = new ArrayList<JTextField>();
        ArrayList<JLabel> labelArray = new ArrayList<JLabel>();

        //Add all textfields to an array
        textFieldArray.add(voornaamField);
        textFieldArray.add(tussenvoegselField);
        textFieldArray.add(achternaamField);
        textFieldArray.add(geslachtField);
        textFieldArray.add(emailField);
        textFieldArray.add(telvastField);
        textFieldArray.add(telmobField);
        textFieldArray.add(opleidingField);

        //Add all specific textfields to an array
        textForStudentArray.add(voornaamField);
        textForStudentArray.add(tussenvoegselField);
        textForStudentArray.add(achternaamField);
        textForStudentArray.add(geslachtField);
        textForStudentArray.add(emailField);
        textForStudentArray.add(telvastField);
        textForStudentArray.add(telmobField);

        //Add all labels to an array
        labelArray.add(voornaamLabel);
        labelArray.add(tussenvoegselLabel);
        labelArray.add(achternaamLabel);
        labelArray.add(geslachtLabel);
        labelArray.add(emailLabel);
        labelArray.add(telvastLabel);
        labelArray.add(telmobLabel);
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

        addButton.setSize(90, 30);
        addButton.setLocation(COMPONENT_WIDTH, ymarg + (textFieldArray.size() * 45));
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Statement statement = null;
                ResultSet resultset = null;
                int oldRowCount = getRows("Student");
                String regexA = "[a-zA-Z]+";
                String regex2 = "[0-9-]+";
                String opleidingText = opleidingField.getText();

                if (voornaamField.getText().isEmpty()
                        || achternaamField.getText().isEmpty()
                        || geslachtField.getText().isEmpty()
                        || emailField.getText().isEmpty()
                        || telvastField.getText().isEmpty()
                        || telmobField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(studentFrame, "Bepaalde velden zijn niet ingevoerd. ");
                } else if (!voornaamField.getText().matches(regexA)
                        || !achternaamField.getText().matches(regexA)
                        || !geslachtField.getText().matches(regexA)
                        || !telvastField.getText().matches(regex2)
                        || !telmobField.getText().matches(regex2)) {
                    JOptionPane.showMessageDialog(studentFrame, "Bepaalde velden zijn niet juist ingevoerd!");
                } else if (!tussenvoegselField.getText().isEmpty() && !tussenvoegselField.getText().matches(regexA)) {
                    JOptionPane.showMessageDialog(studentFrame, "Tussenvoegsel mag alleen letters bevatten!");
                } else if (!geslachtField.getText().equals("Male") && !geslachtField.getText().equals("Female")) {
                    JOptionPane.showMessageDialog(studentFrame, "U moet 'Male' voor man of 'Female' voor vrouw invullen bij geslacht. ");
                } else if (!checkOpleiding(opleidingText)) {
                    JOptionPane.showMessageDialog(studentFrame, "U hebt geen juiste opleiding ID ingevoerd. ");
                } else {
                    try {
                        statement = DataSourceV2.getConnection().createStatement();
                        statement.executeUpdate("INSERT INTO Student (voornaam, tussenvoegsel, achternaam, geslacht, email, telnr_vast, telnr_mob)" + createValuesQuery(textForStudentArray));
                    } catch (SQLException ex) {
                        Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(createValuesQuery(textForStudentArray));

                    if (getRows("Student") == oldRowCount + 1) {
                        int oldHHSRowCount = getRows("HHS_Student");
                        System.out.println(getRows("HHS_Student"));
                        try {
                            statement = DataSourceV2.getConnection().createStatement();
                            statement.executeUpdate("INSERT INTO HHS_Student (student_id, opleiding_id)" + "VALUES(" + getStudentID() + ", " + opleidingText + ")");
                        } catch (SQLException ex) {
                            Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (getRows("HHS_Student") == oldHHSRowCount + 1) {
                            System.out.println(getRows("HHS_Student"));
                            JOptionPane.showMessageDialog(studentFrame, "HHS_Student is toegevoegd!");
                        } else {
                            JOptionPane.showMessageDialog(studentFrame, "Student is niet toegevoegd aan HHS_Student-tabel!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(studentFrame, "Er is iets fouts gegaan bij het toevoegen. Probeer het later opnieuw of neem contact op met uw systeembeheerder.");
                    }
                }
            }
        });

        studentPanel.add(cancelButton);
        studentPanel.add(addButton);
    }

    public void openExchangeStudentFrame() {
        JFrame studentFrame = new JFrame();
        studentFrame.setVisible(true);
        studentFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        studentFrame.setSize(700, 500);

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(null);
        studentFrame.add(studentPanel);

        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Voeg toe");

        JTextField voornaamField = new JTextField();
        JTextField tussenvoegselField = new JTextField();
        JTextField achternaamField = new JTextField();
        JTextField geslachtField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField telvastField = new JTextField();
        JTextField telmobField = new JTextField();
        JTextField huisnummerField = new JTextField();
        JTextField straatField = new JTextField();
        JTextField woonplaatsField = new JTextField();
        JTextField landField = new JTextField();
        JTextField schoolField = new JTextField();

        JLabel voornaamLabel = new JLabel("Voornaam: ");
        JLabel tussenvoegselLabel = new JLabel("Tussenvoegsel: ");
        JLabel achternaamLabel = new JLabel("Achternaam: ");
        JLabel geslachtLabel = new JLabel("Geslacht: ");
        JLabel emailLabel = new JLabel("Email: ");
        JLabel telvastLabel = new JLabel("Telefoonnummer: ");
        JLabel telmobLabel = new JLabel("Mobiele nummer: ");
        JLabel huisnummerLabel = new JLabel("Huisnummer: ");
        JLabel straatLabel = new JLabel("Straat: ");
        JLabel woonplaatsLabel = new JLabel("Woonplaats: ");
        JLabel landLabel = new JLabel("Land: ");
        JLabel schoolLabel = new JLabel("School ID: ");

        ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
        ArrayList<JTextField> textForStudentArray = new ArrayList<JTextField>();
        ArrayList<JLabel> labelArray = new ArrayList<JLabel>();

        //Add all textfields to an array
        textFieldArray.add(voornaamField);
        textFieldArray.add(tussenvoegselField);
        textFieldArray.add(achternaamField);
        textFieldArray.add(geslachtField);
        textFieldArray.add(emailField);
        textFieldArray.add(telvastField);
        textFieldArray.add(telmobField);
        textFieldArray.add(huisnummerField);
        textFieldArray.add(straatField);
        textFieldArray.add(woonplaatsField);
        textFieldArray.add(landField);
        textFieldArray.add(schoolField);

        //Add all labels to an array
        labelArray.add(voornaamLabel);
        labelArray.add(tussenvoegselLabel);
        labelArray.add(achternaamLabel);
        labelArray.add(geslachtLabel);
        labelArray.add(emailLabel);
        labelArray.add(telvastLabel);
        labelArray.add(telmobLabel);
        labelArray.add(huisnummerLabel);
        labelArray.add(straatLabel);
        labelArray.add(woonplaatsLabel);
        labelArray.add(landLabel);
        labelArray.add(schoolLabel);

        //Add all querytextfields for student to an array
        textForStudentArray.add(voornaamField);
        textForStudentArray.add(tussenvoegselField);
        textForStudentArray.add(achternaamField);
        textForStudentArray.add(geslachtField);
        textForStudentArray.add(emailField);
        textForStudentArray.add(telvastField);
        textForStudentArray.add(telmobField);
                
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

        addButton.setSize(90, 30);
        addButton.setLocation(COMPONENT_WIDTH, ymarg + (textFieldArray.size() * 45));
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Statement statement = null;
                ResultSet resultset = null;
                int oldRowCount = getRows("Student");
                String schoolID = schoolField.getText();
                String regexA = "[a-zA-Z]+";
                String regex1 = "[0-9]+";
                String regex2 = "[0-9-]+";

                if (voornaamField.getText().isEmpty()
                        || achternaamField.getText().isEmpty()
                        || geslachtField.getText().isEmpty()
                        || huisnummerField.getText().isEmpty()
                        || straatField.getText().isEmpty()
                        || woonplaatsField.getText().isEmpty()
                        || landField.getText().isEmpty()
                        || schoolField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(studentFrame, "U hebt niet alles ingevuld. ");
                } else if (!voornaamField.getText().matches(regexA)
                        || !achternaamField.getText().matches(regexA)
                        || !geslachtField.getText().matches(regexA)
                        || !huisnummerField.getText().matches(regex1)
                        || !straatField.getText().matches(regexA)
                        || !woonplaatsField.getText().matches(regexA)
                        || !landField.getText().matches(regexA)) {
                    JOptionPane.showMessageDialog(studentFrame, "Bepaalde velden zijn niet juist ingevoerd!");
                } else if (!tussenvoegselField.getText().isEmpty() && !tussenvoegselField.getText().matches(regexA)) {
                    JOptionPane.showMessageDialog(studentFrame, "Tussenvoegsel mag alleen letters bevatten!");
                } else if (!geslachtField.getText().equals("Male") && !geslachtField.getText().equals("Female")) {
                    JOptionPane.showMessageDialog(studentFrame, "U moet 'Male' voor man of 'Female' voor vrouw invullen bij geslacht. ");
                } else if (!checkSchool(schoolID)) {
                    JOptionPane.showMessageDialog(studentFrame, "U hebt geen juiste School ID ingevoerd. ");
                } else {
                    try {
                        statement = DataSourceV2.getConnection().createStatement();
                        statement.executeUpdate("INSERT INTO Student (voornaam, tussenvoegsel, achternaam, geslacht, email, telnr_vast, telnr_mob)" + createValuesQuery(textForStudentArray));
                    } catch (SQLException ex) {
                        Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(createValuesQuery(textForStudentArray));

                    if (getRows("Student") == oldRowCount + 1) {
                        int oldHHSRowCount = getRows("Exchange_Student");
                        try {
                            statement = DataSourceV2.getConnection().createStatement();
                            statement.executeUpdate("INSERT INTO Exchange_Student (student_id, huisnummer, straat, woonplaats, land, school_id) " + "Values(" + getStudentID() + 
                                    ", " + huisnummerField.getText() + ", '" + straatField.getText() + "', '" + ", '" + woonplaatsField.getText() + "', '" + ", '" + landField.getText() + 
                                    "', " + schoolID + ")" );
                        } catch (SQLException ex) {
                            Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (getRows("Exchange_Student") == oldHHSRowCount + 1) {
                            JOptionPane.showMessageDialog(studentFrame, "Exchange_Student is toegevoegd!");
                        } else {
                            JOptionPane.showMessageDialog(studentFrame, "Student is niet toegevoegd aan Exchange_Student-tabel!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(studentFrame, "Er is iets fouts gegaan bij het toevoegen. Probeer het later opnieuw of neem contact op met uw systeembeheerder.");
                    }
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
            if (TFA.size() == 1) {
                sb.append("VALUES ( '" + i.getText().replaceAll("\\s+", "") + "') ");
            } else if (i == TFA.get(0)) {
                sb.append("VALUES ( '" + i.getText().replaceAll("\\s+", "") + "', ");
            } else if (i == TFA.get(TFA.size() - 1)) {
                sb.append("'" + i.getText().replaceAll("\\s+", "") + "')");
            } else {
                sb.append("'" + i.getText().replaceAll("\\s+", "") + "', ");
            }
        }
        return sb.toString();
    }
    
    //Gets the amount of rows a table has
    public int getRows(String table) {
        int rowCount = 0;

        try {
            Statement statement = DataSourceV2.getConnection().createStatement();
            ResultSet resultset = statement.executeQuery("SELECT COUNT(*) FROM " + table);
            resultset.next();
            rowCount = resultset.getInt(1);
            System.out.println(resultset.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean checkOpleiding(String opleidingID) {
        if (isInteger(opleidingID)) {
            try {
                Statement statement = DataSourceV2.getConnection().createStatement();
                ResultSet resultset = statement.executeQuery("SELECT * FROM Opleiding WHERE opleiding_id = " + opleidingID);
                resultset.next();
                if (resultset.getRow() == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean checkSchool(String schoolID) {
        if (isInteger(schoolID)) {
            try {
                Statement statement = DataSourceV2.getConnection().createStatement();
                ResultSet resultset = statement.executeQuery("SELECT * FROM School WHERE school_id = " + schoolID);
                resultset.next();
                if (resultset.getRow() == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public int getStudentID() {
        int studentID = 0;
        try {
            Statement statement = DataSourceV2.getConnection().createStatement();
            ResultSet resultset = statement.executeQuery("SELECT student_id FROM Student ORDER BY student_id DESC LIMIT 1 ");
            resultset.next();
            studentID = resultset.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentID;
    }
}
