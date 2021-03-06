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

    private enum HHSStudentComps{
        voornaam(new JTextField(), new JLabel("Voornaam: ")),
        tussenvoegsel(new JTextField(),new JLabel("Tussenvoegsel: ")),
        achternaam(new JTextField(), new JLabel("Achternaam: ")),
        geslacht(new JTextField(),  new JLabel("Geslacht: ")),
        email(new JTextField(), new JLabel("Email: ")),
        telvast(new JTextField(), new JLabel("Telefoonnummer: ")),
        telmob(new JTextField(), new JLabel("Mobiele nummer: ")),
        opleiding(new JTextField(), new JLabel("Opleiding ID: "));
        
        private JLabel label;
        private JTextField textField;
        
        HHSStudentComps(JTextField textField, JLabel label){
            this.label = label;
            this.textField = textField;
        }
        
        public JTextField getTextField(){
            return textField;
        }
        
        public JLabel getLabel(){
            return label;
        }     
    }
    
    private enum ExStudentComps{
        voornaam(new JTextField(), new JLabel("Voornaam: ")),
        tussenvoegsel(new JTextField(),new JLabel("Tussenvoegsel: ")),
        achternaam(new JTextField(), new JLabel("Achternaam: ")),
        geslacht(new JTextField(),  new JLabel("Geslacht: ")),
        email(new JTextField(), new JLabel("Email: ")),
        telvast(new JTextField(), new JLabel("Telefoonnummer: ")),
        telmob(new JTextField(), new JLabel("Mobiele nummer: ")),
        huisnummer(new JTextField(), new JLabel("Huisnummer: ")),
        straat(new JTextField(), new JLabel("Straatnaam: ")),
        woonplaats(new JTextField(), new JLabel("Woonplaats: ")),
        land(new JTextField(), new JLabel("Land: ")),
        school(new JTextField(), new JLabel("School ID: "));
        
        private JLabel label;
        private JTextField textField;
        
        ExStudentComps(JTextField textField, JLabel label){
            this.label = label;
            this.textField = textField;
        }
        
        public JTextField getTextField(){
            return textField;
        }
        
        public JLabel getLabel(){
            return label;
        }
    }
    
    private enum StageComps{
        naam(new JTextField(), new JLabel("Stagenaam: ")),
        jaar(new JTextField(),new JLabel("Jaar: ")),
        punten(new JTextField(), new JLabel("Aantal punten: ")),
        opleiding(new JTextField(),  new JLabel("Opleiding ID: ")),
        bedrijf(new JTextField(), new JLabel("Bedrijf ID: "));
        
        private JLabel label;
        private JTextField textField;
        
        StageComps(JTextField textField, JLabel label){
            this.label = label;
            this.textField = textField;
        }
        
        public JTextField getTextField(){
            return textField;
        }
        
        public JLabel getLabel(){
            return label;
        }     
    }
    
    private enum StudieComps{
        naam(new JTextField(), new JLabel("Studienaam: ")),
        jaar(new JTextField(),new JLabel("Jaar: ")),
        punten(new JTextField(), new JLabel("Aantal punten: ")),
        opleiding(new JTextField(),  new JLabel("Opleiding ID: ")),
        school(new JTextField(), new JLabel("School ID: ")),
        soort(new JTextField(), new JLabel("Soort studie: "));
        
        private JLabel label;
        private JTextField textField;
        
        StudieComps(JTextField textField, JLabel label){
            this.label = label;
            this.textField = textField;
        }
        
        public JTextField getTextField(){
            return textField;
        }
        
        public JLabel getLabel(){
            return label;
        }   
    }
    
    private enum SchoolComps{
        schoolnaam(new JTextField(), new JLabel("Schoolnaam: ")),
        stad(new JTextField(),new JLabel("Stad: ")),
        land(new JTextField(), new JLabel("Land: "));
        
        private JLabel label;
        private JTextField textField;
        
        SchoolComps(JTextField textField, JLabel label){
            this.label = label;
            this.textField = textField;
        }
        
        public JTextField getTextField(){
            return textField;
        }
        
        public JLabel getLabel(){
            return label;
        }   
    }
    
    private enum BedrijfComps{
        bedrijfsnaam(new JTextField(), new JLabel("Schoolnaam: ")),
        huisnummer(new JTextField(),new JLabel("Huisnummer: ")),
        straat(new JTextField(), new JLabel("Straatnaam: ")),
        stad(new JTextField(), new JLabel("Stad: ")),
        land(new JTextField(), new JLabel("Land: "));
        
        
        private JLabel label;
        private JTextField textField;
        
        BedrijfComps(JTextField textField, JLabel label){
            this.label = label;
            this.textField = textField;
        }
        
        public JTextField getTextField(){
            return textField;
        }
        
        public JLabel getLabel(){
            return label;
        }   
    }
    
    private enum OpleidingComps{
        opleidingnaam(new JTextField(), new JLabel("Opleidingnaam: ")),
        contactpersoon(new JTextField(),new JLabel("Contactpersoon ID: ")),
        school(new JTextField(), new JLabel("Shool ID: "));
        
        
        private JLabel label;
        private JTextField textField;
        
        OpleidingComps(JTextField textField, JLabel label){
            this.label = label;
            this.textField = textField;
        }
        
        public JTextField getTextField(){
            return textField;
        }
        
        public JLabel getLabel(){
            return label;
        }  
    }
    
    private enum ContactComps{
        voornaam(new JTextField(), new JLabel("Voornaam: ")),
        tussenvoegsel(new JTextField(),new JLabel("Tussenvoegsel: ")),
        achternaam(new JTextField(), new JLabel("Achternaam: ")),
        email(new JTextField(), new JLabel("Email: ")),
        telnr(new JTextField(), new JLabel("Telefoon nummer: "));
        
        
        private JLabel label;
        private JTextField textField;
        
        ContactComps(JTextField textField, JLabel label){
            this.label = label;
            this.textField = textField;
        }
        
        public JTextField getTextField(){
            return textField;
        }
        
        public JLabel getLabel(){
            return label;
        }  
    }
    
    JButton addHHSStudentButton = new JButton("Voeg een HHS-student toe");
    JButton addExchangeStudentButton = new JButton("Voeg een Exchange-student toe");
    JButton addStageButton = new JButton("Voeg een Stage toe");
    JButton addStudieButton = new JButton("Voeg een Studie toe");
    JButton addSchoolButton = new JButton("Voeg een School toe");
    JButton addBedrijfButton = new JButton("Voeg een Bedrijf toe");
    JButton addOpleidingButton = new JButton("Voeg een Opleiding toe");
    JButton addContactButton = new JButton("Voeg een Contactpersoon toe");

    public AddPanel(int width, int height) {
        super(width, height);
        addComponents();
    }

    public void addComponents() {
        
        int xmarg = 15;
        int ymarg = 15;
        
        //HHSStudentButton
        addHHSStudentButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        addHHSStudentButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {               
                openHHSStudentFrame();
            }
        });

        //ExchangeStudentButton
        addExchangeStudentButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        addExchangeStudentButton.setLocation(addHHSStudentButton.getX() + COMPONENT_WIDTH + xmarg, addHHSStudentButton.getY());
        addExchangeStudentButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                openExchangeStudentFrame();
            }
        });
        
        //StageButton
        addStageButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        addStageButton.setLocation(addHHSStudentButton.getX(), addHHSStudentButton.getY() + COMPONENT_HEIGHT + ymarg);
        addStageButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                openStageFrame();
            }
        });
        
        //StudieButton
        addStudieButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        addStudieButton.setLocation(addHHSStudentButton.getX() + COMPONENT_WIDTH + xmarg, addHHSStudentButton.getY() + COMPONENT_HEIGHT + ymarg);
        addStudieButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                openStudieFrame();
            }
        });
        
        //SchoolButton
        addSchoolButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        addSchoolButton.setLocation(addHHSStudentButton.getX(), addHHSStudentButton.getY() + (COMPONENT_HEIGHT * 2) + (ymarg * 2));
        addSchoolButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                openSchoolFrame();
            }
        });
        
        //BedrijfButton
        addBedrijfButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        addBedrijfButton.setLocation(addHHSStudentButton.getX() + COMPONENT_WIDTH + xmarg, addHHSStudentButton.getY() + (COMPONENT_HEIGHT * 2) + (ymarg * 2));
        addBedrijfButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                openBedrijfFrame();
            }
        });
        
        //OpleidingButton
        addOpleidingButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        addOpleidingButton.setLocation(addHHSStudentButton.getX(), addHHSStudentButton.getY() + (COMPONENT_HEIGHT * 3)+ (ymarg * 3));
        addOpleidingButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                openOpleidingFrame();
            }
        });
        
        //ContactButton
        addContactButton.setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
        addContactButton.setLocation(addHHSStudentButton.getX() + COMPONENT_WIDTH+ xmarg, addHHSStudentButton.getY() + (COMPONENT_HEIGHT * 3)+ (ymarg * 3));
        addContactButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                openContactFrame();
            }
        });

        //Add buttons to frame
        add(addHHSStudentButton);
        add(addExchangeStudentButton);
        add(addStageButton);
        add(addStudieButton);
        add(addSchoolButton);
        add(addBedrijfButton);
        add(addOpleidingButton);
        add(addContactButton);
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

        ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
        ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
        ArrayList<JTextField> textForStudentArray = new ArrayList<JTextField>(); 
        
        HHSStudentComps[] StudentArray = HHSStudentComps.values();

        for(HHSStudentComps i : StudentArray){
            textFieldArray.add(i.getTextField());
            labelArray.add(i.getLabel());
        }
        
//        //Add all specific textfields to an array
        textForStudentArray.add(textFieldArray.get(0));
        textForStudentArray.add(textFieldArray.get(1));
        textForStudentArray.add(textFieldArray.get(2));
        textForStudentArray.add(textFieldArray.get(3));
        textForStudentArray.add(textFieldArray.get(4));
        textForStudentArray.add(textFieldArray.get(5));
        textForStudentArray.add(textFieldArray.get(6));

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
                String regexA = "[a-zA-Z ]+";
                String regex2 = "[0-9-]+";
                String opleidingText = textFieldArray.get(7).getText();

                if (textFieldArray.get(0).getText().isEmpty()
                        || textFieldArray.get(2).getText().isEmpty()
                        || textFieldArray.get(3).getText().isEmpty()
                        || textFieldArray.get(4).getText().isEmpty()
                        || textFieldArray.get(5).getText().isEmpty()
                        || textFieldArray.get(6).getText().isEmpty()) {
                    JOptionPane.showMessageDialog(studentFrame, "Bepaalde velden zijn niet ingevoerd. ");
                } else if (!textFieldArray.get(0).getText().matches(regexA)
                        || !textFieldArray.get(2).getText().matches(regexA)
                        || !textFieldArray.get(3).getText().matches(regexA)
                        || !textFieldArray.get(5).getText().matches(regex2)
                        || !textFieldArray.get(6).getText().matches(regex2)) {
                    JOptionPane.showMessageDialog(studentFrame, "Bepaalde velden zijn niet juist ingevoerd!");
                } else if (!textFieldArray.get(1).getText().isEmpty() && !textFieldArray.get(1).getText().matches(regexA)) {
                    JOptionPane.showMessageDialog(studentFrame, "Tussenvoegsel mag alleen letters bevatten!");
                } else if (!textFieldArray.get(3).getText().equals("Male") && !textFieldArray.get(3).getText().equals("Female")) {
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
                            statement.executeUpdate("INSERT INTO HHS_Student (student_id, opleiding_id)" + "VALUES(" + getLastStudentID() + ", " + opleidingText + ")");
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
        if(!studentFrame.isActive()){
          studentFrame.setVisible(true);          
        }

        studentFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        studentFrame.setSize(700, 650);

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(null);
        studentFrame.add(studentPanel);

        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Voeg toe");

        ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
        ArrayList<JTextField> textForStudentArray = new ArrayList<JTextField>();
        ArrayList<JTextField> textForExStudentArray = new ArrayList<JTextField>();
        ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
        
        ExStudentComps[] StudentArray = ExStudentComps.values();

        for(ExStudentComps i : StudentArray){
            textFieldArray.add(i.getTextField());
            labelArray.add(i.getLabel());
        }

        //Add all querytextfields for student to an array
        textForStudentArray.add(textFieldArray.get(0));
        textForStudentArray.add(textFieldArray.get(1));
        textForStudentArray.add(textFieldArray.get(2));
        textForStudentArray.add(textFieldArray.get(3));
        textForStudentArray.add(textFieldArray.get(4));
        textForStudentArray.add(textFieldArray.get(5));
        textForStudentArray.add(textFieldArray.get(6));
        
        //Add all querytextfields for Exstudent to an array
        textForExStudentArray.add(textFieldArray.get(7));
        textForExStudentArray.add(textFieldArray.get(8));
        textForExStudentArray.add(textFieldArray.get(9));
        textForExStudentArray.add(textFieldArray.get(10));
        textForExStudentArray.add(textFieldArray.get(11));
                
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
                String schoolID = textFieldArray.get(11).getText();
                String regexA = "[a-zA-Z]+";
                String regexB = "[a-zA-Z ]+";
                String regex1 = "[0-9]+";
                String regex2 = "[0-9-()]+";

                if (textFieldArray.get(0).getText().isEmpty()
                        || textFieldArray.get(2).getText().isEmpty()
                        || textFieldArray.get(3).getText().isEmpty()
                        || textFieldArray.get(4).getText().isEmpty()
                        || textFieldArray.get(8).getText().isEmpty()
                        || textFieldArray.get(9).getText().isEmpty()
                        || textFieldArray.get(10).getText().isEmpty()
                        || textFieldArray.get(11).getText().isEmpty()) {
                    JOptionPane.showMessageDialog(studentFrame, "U hebt niet alles ingevuld. ");
                } else if (!textFieldArray.get(0).getText().matches(regexA)
                        || !textFieldArray.get(2).getText().matches(regexA)
                        || !textFieldArray.get(3).getText().matches(regexA)
                        || !textFieldArray.get(7).getText().matches(regex1)
                        || !textFieldArray.get(8).getText().matches(regexB)
                        || !textFieldArray.get(9).getText().matches(regexB)
                        || !textFieldArray.get(10).getText().matches(regexB)
                        || !textFieldArray.get(5).getText().matches(regex2)
                        || !textFieldArray.get(6).getText().matches(regex2)) {
                    JOptionPane.showMessageDialog(studentFrame, "Bepaalde velden zijn niet juist ingevoerd!");
                } else if (!textFieldArray.get(1).getText().isEmpty() && !textFieldArray.get(1).getText().matches(regexB)) {
                    JOptionPane.showMessageDialog(studentFrame, "Tussenvoegsel mag alleen letters bevatten!");
                } else if (!textFieldArray.get(3).getText().equals("Male") && !textFieldArray.get(3).getText().equals("Female")) {
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
                            statement.executeUpdate("INSERT INTO Exchange_Student (student_id, huisnummer, straat, woonplaats, land, school_id) " + createOtherValuesQuery(textForExStudentArray, getLastStudentID()));
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

    public void openStageFrame(){
        JFrame stageFrame = new JFrame();
        stageFrame.setVisible(true);
        stageFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        stageFrame.setSize(700, 500);

        JPanel stagePanel = new JPanel();
        stagePanel.setLayout(null);
        stageFrame.add(stagePanel);

        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Voeg toe");
        
        ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
        ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
        ArrayList<JTextField> textArray = new ArrayList<JTextField>();
        
        StageComps[] StudentArray = StageComps.values();

        for(StageComps i : StudentArray){
            textFieldArray.add(i.getTextField());
            labelArray.add(i.getLabel());
        }
        
        textArray.add(textFieldArray.get(0));
        textArray.add(textFieldArray.get(1));
        textArray.add(textFieldArray.get(2));
        textArray.add(textFieldArray.get(3));
        
        int xmarg = 180;
        int ymarg = 15;

        for (int i = 0; i < textFieldArray.size(); i++) {
            textFieldArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            textFieldArray.get(i).setLocation(xmarg, ymarg + i * 45);
            labelArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            labelArray.get(i).setLocation(textFieldArray.get(i).getX() - 155, textFieldArray.get(i).getY());

            stagePanel.add(textFieldArray.get(i));
            stagePanel.add(labelArray.get(i));
        }
        
        cancelButton.setSize(90, 30);
        cancelButton.setLocation(20, ymarg + (textFieldArray.size() * 45));
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                stageFrame.dispose();
            }
        });

        addButton.setSize(90, 30);
        addButton.setLocation(COMPONENT_WIDTH, ymarg + (textFieldArray.size() * 45));
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Statement statement = null;
                ResultSet resultset = null;
                int oldRowCount = getRows("Traject");
                String regexA = "[a-zA-Z]+";
                String regexB = "[a-zA-Z ]+";
                String regex1 = "[0-9]+";
                String regex2 = "[0-9-()]+";
                
            if (textFieldArray.get(0).getText().isEmpty()
                        || textFieldArray.get(1).getText().isEmpty()
                        || textFieldArray.get(2).getText().isEmpty()
                        || textFieldArray.get(3).getText().isEmpty()
                        || textFieldArray.get(4).getText().isEmpty()) {
                    JOptionPane.showMessageDialog(stageFrame, "U hebt niet alles ingevuld. ");
                } else if (!textFieldArray.get(0).getText().matches(regexA)
                        || !textFieldArray.get(1).getText().matches(regex1)
                        || !textFieldArray.get(2).getText().matches(regex1)) {
                    JOptionPane.showMessageDialog(stageFrame, "Bepaalde velden zijn niet juist ingevoerd!");
                }  else if (!checkOpleiding(textFieldArray.get(3).getText())) {
                    JOptionPane.showMessageDialog(stageFrame, "U hebt geen juiste School ID ingevoerd. ");
                } else if (!checkBedrijf(textFieldArray.get(4).getText())) {
                    JOptionPane.showMessageDialog(stageFrame, "U hebt geen juiste Bedrijf ID ingevoerd. ");
                } else {
                    try {
                        statement = DataSourceV2.getConnection().createStatement();
                        statement.executeUpdate("INSERT INTO Traject (naam, jaar, punten, opleiding_id)" + createValuesQuery(textArray));
                    } catch (SQLException ex) {
                        Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (getRows("Traject") == oldRowCount + 1) {
                        int oldRowCount2 = getRows("Stage");
                        try {
                            statement = DataSourceV2.getConnection().createStatement();
                            statement.executeUpdate("INSERT INTO Stage (traject_id, bedrijf_id) " + "VALUES ('" + getLastTrajectID() + "', '" + textFieldArray.get(4).getText() + "')");
                        } catch (SQLException ex) {
                            Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (getRows("Stage") == oldRowCount2 + 1) {
                            JOptionPane.showMessageDialog(stageFrame, "Traject is toegevoegd!");
                        } else {
                            JOptionPane.showMessageDialog(stageFrame, "Traject is niet toegevoegd aan Stage-tabel!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(stageFrame, "Er is iets fouts gegaan bij het toevoegen. Probeer het later opnieuw of neem contact op met uw systeembeheerder.");
                    }
                }
            }
        });
        
        stagePanel.add(cancelButton);
        stagePanel.add(addButton);
    }
    
    public void openStudieFrame(){
        JFrame studieFrame = new JFrame();
        studieFrame.setVisible(true);
        studieFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        studieFrame.setSize(700, 500);

        JPanel studiePanel = new JPanel();
        studiePanel.setLayout(null);
        studieFrame.add(studiePanel);

        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Voeg toe");
        
        ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
        ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
        ArrayList<JTextField> textArray = new ArrayList<JTextField>();
        
        StudieComps[] StudentArray = StudieComps.values();

        for(StudieComps i : StudentArray){
            textFieldArray.add(i.getTextField());
            labelArray.add(i.getLabel());
        }
        
        textArray.add(textFieldArray.get(0));
        textArray.add(textFieldArray.get(1));
        textArray.add(textFieldArray.get(2));
        textArray.add(textFieldArray.get(3));
        
        int xmarg = 180;
        int ymarg = 15;

        for (int i = 0; i < textFieldArray.size(); i++) {
            textFieldArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            textFieldArray.get(i).setLocation(xmarg, ymarg + i * 45);
            labelArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            labelArray.get(i).setLocation(textFieldArray.get(i).getX() - 155, textFieldArray.get(i).getY());

            studiePanel.add(textFieldArray.get(i));
            studiePanel.add(labelArray.get(i));
        }
        
        cancelButton.setSize(90, 30);
        cancelButton.setLocation(20, ymarg + (textFieldArray.size() * 45));
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                studieFrame.dispose();
            }
        });

        addButton.setSize(90, 30);
        addButton.setLocation(COMPONENT_WIDTH, ymarg + (textFieldArray.size() * 45));
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Statement statement = null;
                ResultSet resultset = null;
                int oldRowCount = getRows("Traject");
                String regexA = "[a-zA-Z]+";
                String regexB = "[a-zA-Z ]+";
                String regex1 = "[0-9]+";
                String regex2 = "[0-9-()]+";
                
            if (textFieldArray.get(0).getText().isEmpty()
                        || textFieldArray.get(1).getText().isEmpty()
                        || textFieldArray.get(2).getText().isEmpty()
                        || textFieldArray.get(3).getText().isEmpty()
                        || textFieldArray.get(4).getText().isEmpty()
                        || textFieldArray.get(5).getText().isEmpty()) {
                    JOptionPane.showMessageDialog(studieFrame, "U hebt niet alles ingevuld. ");
                } else if (!textFieldArray.get(0).getText().matches(regexA)
                        || !textFieldArray.get(1).getText().matches(regex1)
                        || !textFieldArray.get(2).getText().matches(regex1)
                        || !textFieldArray.get(4).getText().matches(regex1)) {
                    JOptionPane.showMessageDialog(studieFrame, "Bepaalde velden zijn niet juist ingevoerd!");
                } else if (!textFieldArray.get(5).getText().equals("Minor") && !textFieldArray.get(5).getText().equals("EPS") && !textFieldArray.get(5).getText().equals("Summerschool")){
                    JOptionPane.showMessageDialog(studieFrame, "De soort opleiding kan alleen bestaan uit: EPS, Summerschool of Minor");
                } else if (!checkOpleiding(textFieldArray.get(3).getText())) {
                    JOptionPane.showMessageDialog(studieFrame, "U hebt geen juiste Opleiding ID ingevoerd. ");
                } else if (!checkBedrijf(textFieldArray.get(4).getText())) {
                    JOptionPane.showMessageDialog(studieFrame, "U hebt geen juiste School ID ingevoerd. ");
                } else {
                    try {
                        statement = DataSourceV2.getConnection().createStatement();
                        statement.executeUpdate("INSERT INTO Traject (naam, jaar, punten, opleiding_id)" + createValuesQuery(textArray));
                    } catch (SQLException ex) {
                        Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (getRows("Traject") == oldRowCount + 1) {
                        int oldRowCount2 = getRows("Studie");
                        try {
                            statement = DataSourceV2.getConnection().createStatement();
                            statement.executeUpdate("INSERT INTO Studie (traject_id, school_id, soort) " + "VALUES ('" + getLastTrajectID() + "', '" + textFieldArray.get(4).getText() + "', '" + textFieldArray.get(5).getText() + "')");
                        } catch (SQLException ex) {
                            Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (getRows("Studie") == oldRowCount2 + 1) {
                            JOptionPane.showMessageDialog(studieFrame, "Traject is toegevoegd!");
                        } else {
                            JOptionPane.showMessageDialog(studieFrame, "Traject is niet toegevoegd aan Studie-tabel!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(studieFrame, "Er is iets fouts gegaan bij het toevoegen. Probeer het later opnieuw of neem contact op met uw systeembeheerder.");
                    }
                }
            }
        });
        
        studiePanel.add(cancelButton);
        studiePanel.add(addButton);  
    }
    
    public void openSchoolFrame(){
        JFrame schoolFrame = new JFrame();
        schoolFrame.setVisible(true);
        schoolFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        schoolFrame.setSize(700, 500);

        JPanel schoolPanel = new JPanel();
        schoolPanel.setLayout(null);
        schoolFrame.add(schoolPanel);

        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Voeg toe");
        
        ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
        ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
        ArrayList<JTextField> textArray = new ArrayList<JTextField>();
        
        SchoolComps[] StudentArray = SchoolComps.values();

        for(SchoolComps i : StudentArray){
            textFieldArray.add(i.getTextField());
            labelArray.add(i.getLabel());
        }
        
        textArray.add(textFieldArray.get(0));
        textArray.add(textFieldArray.get(1));
        textArray.add(textFieldArray.get(2));
        
        int xmarg = 180;
        int ymarg = 15;

        for (int i = 0; i < textFieldArray.size(); i++) {
            textFieldArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            textFieldArray.get(i).setLocation(xmarg, ymarg + i * 45);
            labelArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            labelArray.get(i).setLocation(textFieldArray.get(i).getX() - 155, textFieldArray.get(i).getY());

            schoolPanel.add(textFieldArray.get(i));
            schoolPanel.add(labelArray.get(i));
        }
        
        cancelButton.setSize(90, 30);
        cancelButton.setLocation(20, ymarg + (textFieldArray.size() * 45));
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                schoolFrame.dispose();
            }
        });

        addButton.setSize(90, 30);
        addButton.setLocation(COMPONENT_WIDTH, ymarg + (textFieldArray.size() * 45));
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Statement statement = null;
                ResultSet resultset = null;
                int oldRowCount = getRows("School");
                String regexA = "[a-zA-Z]+";
                String regexB = "[a-zA-Z ]+";
                String regex1 = "[0-9]+";
                String regex2 = "[0-9-()]+";
                
                System.out.println(createValuesQuery(textArray));
                
            if (textFieldArray.get(0).getText().isEmpty()
                        || textFieldArray.get(1).getText().isEmpty()
                        || textFieldArray.get(2).getText().isEmpty()) {
                    JOptionPane.showMessageDialog(schoolFrame, "U hebt niet alles ingevuld. ");
                } else if (!textFieldArray.get(0).getText().matches(regexB)
                        || !textFieldArray.get(1).getText().matches(regexB)
                        || !textFieldArray.get(2).getText().matches(regexB)) {
                    JOptionPane.showMessageDialog(schoolFrame, "Bepaalde velden zijn niet juist ingevoerd!");
                } else {
                    try {
                        statement = DataSourceV2.getConnection().createStatement();
                        statement.executeUpdate("INSERT INTO School (schoolnaam, stad, land)" + createValuesQuery(textArray));
                    } catch (SQLException ex) {
                        Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (getRows("School") == oldRowCount + 1) {
                        JOptionPane.showMessageDialog(schoolFrame, "School is toegevoegd!");
                    } else {
                        JOptionPane.showMessageDialog(schoolFrame, "Er is iets fouts gegaan. Probeer later opnieuw.");
                    }         
                }
            }
        });
        
        schoolPanel.add(cancelButton);
        schoolPanel.add(addButton);  
    }
    
    public void openBedrijfFrame(){
        JFrame bedrijfFrame = new JFrame();
        bedrijfFrame.setVisible(true);
        bedrijfFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        bedrijfFrame.setSize(700, 500);

        JPanel bedrijfPanel = new JPanel();
        bedrijfPanel.setLayout(null);
        bedrijfFrame.add(bedrijfPanel);

        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Voeg toe");
        
        ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
        ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
        ArrayList<JTextField> textArray = new ArrayList<JTextField>();
        
        BedrijfComps[] StudentArray = BedrijfComps.values();

        for(BedrijfComps i : StudentArray){
            textFieldArray.add(i.getTextField());
            labelArray.add(i.getLabel());
        }
        
        textArray.add(textFieldArray.get(0));
        textArray.add(textFieldArray.get(1));
        textArray.add(textFieldArray.get(2));
        textArray.add(textFieldArray.get(3));
        textArray.add(textFieldArray.get(4));
        
        int xmarg = 180;
        int ymarg = 15;

        for (int i = 0; i < textFieldArray.size(); i++) {
            textFieldArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            textFieldArray.get(i).setLocation(xmarg, ymarg + i * 45);
            labelArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            labelArray.get(i).setLocation(textFieldArray.get(i).getX() - 155, textFieldArray.get(i).getY());

            bedrijfPanel.add(textFieldArray.get(i));
            bedrijfPanel.add(labelArray.get(i));
        }
        
        cancelButton.setSize(90, 30);
        cancelButton.setLocation(20, ymarg + (textFieldArray.size() * 45));
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                bedrijfFrame.dispose();
            }
        });

        addButton.setSize(90, 30);
        addButton.setLocation(COMPONENT_WIDTH, ymarg + (textFieldArray.size() * 45));
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Statement statement = null;
                ResultSet resultset = null;
                int oldRowCount = getRows("Bedrijf");
                String regexA = "[a-zA-Z]+";
                String regexB = "[a-zA-Z ]+";
                String regex1 = "[0-9a-z]+";
                String regex2 = "[0-9-()]+";
                
                System.out.println(createValuesQuery(textArray));
                
            if (textFieldArray.get(0).getText().isEmpty()
                        || textFieldArray.get(1).getText().isEmpty()
                        || textFieldArray.get(2).getText().isEmpty()
                        || textFieldArray.get(3).getText().isEmpty()
                        || textFieldArray.get(4).getText().isEmpty()) {
                    JOptionPane.showMessageDialog(bedrijfFrame, "U hebt niet alles ingevuld. ");
                } else if (!textFieldArray.get(0).getText().matches(regexB)
                        || !textFieldArray.get(1).getText().matches(regex1)
                        || !textFieldArray.get(2).getText().matches(regexB)
                        || !textFieldArray.get(3).getText().matches(regexB)
                        || !textFieldArray.get(4).getText().matches(regexB)) {
                    JOptionPane.showMessageDialog(bedrijfFrame, "Bepaalde velden zijn niet juist ingevoerd!");
                } else {
                    try {
                        statement = DataSourceV2.getConnection().createStatement();
                        statement.executeUpdate("INSERT INTO Bedrijf (bedrijfsnaam, huisnr, straat, stad, land)" + createValuesQuery(textArray));
                    } catch (SQLException ex) {
                        Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (getRows("Bedrijf") == oldRowCount + 1) {
                        JOptionPane.showMessageDialog(bedrijfFrame, "Bedrijf is toegevoegd!");
                    } else {
                        JOptionPane.showMessageDialog(bedrijfFrame, "Er is iets fouts gegaan. Probeer later opnieuw.");
                    }         
                }
            }
        });
        
        bedrijfPanel.add(cancelButton);
        bedrijfPanel.add(addButton);  
    }
    
    public void openOpleidingFrame(){
        JFrame opleidingFrame = new JFrame();
        opleidingFrame.setVisible(true);
        opleidingFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        opleidingFrame.setSize(700, 500);

        JPanel opleidingPanel = new JPanel();
        opleidingPanel.setLayout(null);
        opleidingFrame.add(opleidingPanel);

        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Voeg toe");

        ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
        ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
        ArrayList<JTextField> textForStudentArray = new ArrayList<JTextField>(); 
        
        OpleidingComps[] StudentArray = OpleidingComps.values();

        for(OpleidingComps i : StudentArray){
            textFieldArray.add(i.getTextField());
            labelArray.add(i.getLabel());
        }
        
        //Add all specific textfields to an array
        textForStudentArray.add(textFieldArray.get(0));
        textForStudentArray.add(textFieldArray.get(1));
        textForStudentArray.add(textFieldArray.get(2));

        int xmarg = 180;
        int ymarg = 15;

        for (int i = 0; i < textFieldArray.size(); i++) {
            textFieldArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            textFieldArray.get(i).setLocation(xmarg, ymarg + i * 45);
            labelArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            labelArray.get(i).setLocation(textFieldArray.get(i).getX() - 155, textFieldArray.get(i).getY());

            opleidingPanel.add(textFieldArray.get(i));
            opleidingPanel.add(labelArray.get(i));
        }

        cancelButton.setSize(90, 30);
        cancelButton.setLocation(20, ymarg + (textFieldArray.size() * 45));
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                opleidingFrame.dispose();
            }
        });

        addButton.setSize(90, 30);
        addButton.setLocation(COMPONENT_WIDTH, ymarg + (textFieldArray.size() * 45));
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Statement statement = null;
                ResultSet resultset = null;
                int oldRowCount = getRows("Opleiding");
                String regexA = "[a-zA-Z ]+";
                String regex2 = "[0-9]+";

                if (textFieldArray.get(0).getText().isEmpty()
                        || textFieldArray.get(1).getText().isEmpty()
                        || textFieldArray.get(2).getText().isEmpty()) {
                    JOptionPane.showMessageDialog(opleidingFrame, "Bepaalde velden zijn niet ingevoerd. ");
                } else if (!textFieldArray.get(0).getText().matches(regexA)
                        || !textFieldArray.get(1).getText().matches(regex2)
                        || !textFieldArray.get(2).getText().matches(regex2)) {
                    JOptionPane.showMessageDialog(opleidingFrame, "Bepaalde velden zijn niet juist ingevoerd!");
                } else if (!checkContact(textFieldArray.get(1).getText())) {
                    JOptionPane.showMessageDialog(opleidingFrame, "U hebt geen juiste contact ID ingevoerd. ");
                } else if (!checkSchool(textFieldArray.get(2).getText())) {
                    JOptionPane.showMessageDialog(opleidingFrame, "U hebt geen juiste school ID ingevoerd. ");
                } else {
                    try {
                        statement = DataSourceV2.getConnection().createStatement();
                        statement.executeUpdate("INSERT INTO Opleiding (opleiding_naam, contpers_id, school_id)" + createValuesQuery(textForStudentArray));
                    } catch (SQLException ex) {
                        Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (getRows("Opleiding") == oldRowCount + 1) {
                        JOptionPane.showMessageDialog(opleidingFrame, "Opleiding is toegevoegd.");
                    } else {
                        JOptionPane.showMessageDialog(opleidingFrame, "Er is iets fouts gegaan bij het toevoegen. Probeer het later opnieuw of neem contact op met uw systeembeheerder.");
                    }
                }
            }
        });

        opleidingPanel.add(cancelButton);
        opleidingPanel.add(addButton);
     
    }
    
    public void openContactFrame(){
        JFrame contactFrame = new JFrame();
        contactFrame.setVisible(true);
        contactFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contactFrame.setSize(700, 500);

        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(null);
        contactFrame.add(contactPanel);

        JButton cancelButton = new JButton("Cancel");
        JButton addButton = new JButton("Voeg toe");

        ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
        ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
        ArrayList<JTextField> textForStudentArray = new ArrayList<JTextField>(); 
        
        ContactComps[] StudentArray = ContactComps.values();

        for(ContactComps i : StudentArray){
            textFieldArray.add(i.getTextField());
            labelArray.add(i.getLabel());
        }
        
        //Add all specific textfields to an array
        textForStudentArray.add(textFieldArray.get(0));
        textForStudentArray.add(textFieldArray.get(1));
        textForStudentArray.add(textFieldArray.get(2));
        textForStudentArray.add(textFieldArray.get(3));
        textForStudentArray.add(textFieldArray.get(4));

        int xmarg = 180;
        int ymarg = 15;

        for (int i = 0; i < textFieldArray.size(); i++) {
            textFieldArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            textFieldArray.get(i).setLocation(xmarg, ymarg + i * 45);
            labelArray.get(i).setSize(COMPONENT_WIDTH, COMPONENT_HEIGHT);
            labelArray.get(i).setLocation(textFieldArray.get(i).getX() - 155, textFieldArray.get(i).getY());

            contactPanel.add(textFieldArray.get(i));
            contactPanel.add(labelArray.get(i));
        }

        cancelButton.setSize(90, 30);
        cancelButton.setLocation(20, ymarg + (textFieldArray.size() * 45));
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                contactFrame.dispose();
            }
        });

        addButton.setSize(90, 30);
        addButton.setLocation(COMPONENT_WIDTH, ymarg + (textFieldArray.size() * 45));
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Statement statement = null;
                ResultSet resultset = null;
                int oldRowCount = getRows("Contactpersoon");
                String regexA = "[a-zA-Z ]+";
                String regex2 = "[0-9-()]+";

                if (textFieldArray.get(0).getText().isEmpty()
                        || textFieldArray.get(1).getText().isEmpty()
                        || textFieldArray.get(2).getText().isEmpty()
                        || textFieldArray.get(3).getText().isEmpty()
                        || textFieldArray.get(4).getText().isEmpty()) {
                    JOptionPane.showMessageDialog(contactFrame, "Bepaalde velden zijn niet ingevoerd. ");
                } else if (!textFieldArray.get(0).getText().matches(regexA)
                        || !textFieldArray.get(1).getText().matches(regexA)
                        || !textFieldArray.get(2).getText().matches(regexA)
                        || !textFieldArray.get(4).getText().matches(regex2)) {
                    JOptionPane.showMessageDialog(contactFrame, "Bepaalde velden zijn niet juist ingevoerd!");
                } else {
                    try {
                        statement = DataSourceV2.getConnection().createStatement();
                        statement.executeUpdate("INSERT INTO Contactpersoon (voornaam, tussenvoegsel, achternaam, email, telnr)" + createValuesQuery(textForStudentArray));
                    } catch (SQLException ex) {
                        Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (getRows("Contactpersoon") == oldRowCount + 1) {
                        JOptionPane.showMessageDialog(contactFrame, "Contactpersoon is toegevoegd.");
                    } else {
                        JOptionPane.showMessageDialog(contactFrame, "Er is iets fouts gegaan bij het toevoegen. Probeer het later opnieuw of neem contact op met uw systeembeheerder.");
                    }
                }
            }
        });

        contactPanel.add(cancelButton);
        contactPanel.add(addButton);
     
    }
    
    //Creates a query with values based on the amount of textfields | TFA stands for TextFieldArray
    public String createValuesQuery(ArrayList<JTextField> TFA) {
        StringBuilder sb = new StringBuilder();
        for (JTextField i : TFA) {
            if (TFA.size() == 1) {
                sb.append("VALUES ( '" + i.getText() + "') ");
            } else if (i == TFA.get(0)) {
                sb.append("VALUES ( '" + i.getText() + "', ");
            } else if (i == TFA.get(TFA.size() - 1)) {
                sb.append("'" + i.getText() + "')");
            } else {
                sb.append("'" + i.getText() + "', ");
            }
        }
        return sb.toString();
    }
    
    
    
    //Creates query with values based on textfield but with studentID
    public String createOtherValuesQuery(ArrayList<JTextField> TFA, int studentID) {
        StringBuilder sb = new StringBuilder();
        for (JTextField i : TFA) {
            if (TFA.size() == 1) {
                sb.append("VALUES ('" + studentID + "', '" + i.getText() + "') ");
            } else if (i == TFA.get(0)) {
                sb.append("VALUES ('" + studentID + "', '" + i.getText() + "', ");
            } else if (i == TFA.get(TFA.size() - 1)) {
                sb.append("'" + i.getText() + "')");
            } else {
                sb.append("'" + i.getText() + "', ");
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

    public boolean checkBedrijf(String bedrijfID){
        if (isInteger(bedrijfID)) {
            try {
                Statement statement = DataSourceV2.getConnection().createStatement();
                ResultSet resultset = statement.executeQuery("SELECT * FROM Bedrijf WHERE bedrijf_id = " + bedrijfID);
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
    
        public boolean checkContact(String ContactID){
        if (isInteger(ContactID)) {
            try {
                Statement statement = DataSourceV2.getConnection().createStatement();
                ResultSet resultset = statement.executeQuery("SELECT * FROM Contactpersoon WHERE contpers_id = " + ContactID);
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
    
    public int getLastStudentID() {
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
    
        public int getLastTrajectID() {
        int trajectID = 0;
        try {
            Statement statement = DataSourceV2.getConnection().createStatement();
            ResultSet resultset = statement.executeQuery("SELECT traject_id FROM Traject ORDER BY traject_id DESC LIMIT 1 ");
            resultset.next();
            trajectID = resultset.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AddPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trajectID;
    }
}