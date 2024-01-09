import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    public JFrame frame;
    public JButton loginButton;
    public JButton registerButton;
    public JButton viewCompetitionButton;
    public JButton exitButton;
    public Manager manager = new Manager();
    public CompetitorList competitorList = manager.getCompetitorList();
    public StaffList staffList = manager.getStaffList();
    public CompetitionList competitionList = manager.getCompetitionList();

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new 
            GUI().display();
            }
        });
    }

    public 
GUI() {
        frame = new JFrame("Main Menu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        manager.init();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        viewCompetitionButton = new JButton("View Competition");
        exitButton = new JButton("Exit");

        JPanel panel = new JPanel();
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(viewCompetitionButton);
        panel.add(exitButton);

        frame.add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLoginButtonClick();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegisterButtonClick();
            }
        });

        viewCompetitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewCompetitionButtonClick();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void display() {
        frame.setVisible(true);
    }

    private void handleLoginButtonClick() {
        String userID = JOptionPane.showInputDialog(frame, "Enter your user ID:");
        if (userID != null && !userID.isEmpty()) {
            int userId = Integer.parseInt(userID);
            Competitor competitor = manager.getCompetitorById(userId);
            Staff staff = manager.getStaffById(userId);
            if (competitor != null) {
                System.out.println("Logged in as: " + competitor.getName());
                    launchCompetitorInterface(competitor);
            } else if (staff != null) {
                System.out.println("Logged in as: " + staff.getName());
                if (staff instanceof Official) {
                    launchOfficialInterface((Official) staff);
                } else {
                    launchStaffInterface(staff);
                }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid user ID. Please enter a valid ID.");
        }
    }
    }
    

    private void launchOfficialInterface(Official staff){
        OfficialInterface officialInterface = new OfficialInterface(staff, manager);
        officialInterface.display();
        frame.dispose();
    }

    private void launchStaffInterface(Staff staff){ 
        StaffInterface staffInterface = new StaffInterface(staff, manager);
        staffInterface.display();
        frame.dispose();
    }

    private void launchCompetitorInterface(Competitor competitor) {
        CompetitorInterface competitorInterface = new CompetitorInterface(competitor, manager);
        competitorInterface.display();
        frame.dispose();
    }
    private void handleRegisterButtonClick() {
        JFrame registerFrame = new JFrame("Register Competitor");
        registerFrame.setSize(400, 300);
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2));

        JTextField nameField = new JTextField();
        JTextField countryField = new JTextField();
        JTextField genderField = new JTextField();

        JComboBox<String> skillLevelComboBox = new JComboBox<>(new String[]{"Novice", "Intermediate", "Expert"});

        JTextField ageField = new JTextField();

        JComboBox<String> competitorTypeComboBox = new JComboBox<>(new String[]{"VolleyballIndoors", "VolleyballOutdoors"});

        JButton confirmButton = new JButton("Confirm");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Country:"));
        panel.add(countryField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderField);
        panel.add(new JLabel("Level:"));
        panel.add(skillLevelComboBox);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Competitor Type:"));
        panel.add(competitorTypeComboBox);
        panel.add(confirmButton);

        registerFrame.add(panel);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();

                String country = countryField.getText();

                String gender = genderField.getText();

                String skillLevel = (String) skillLevelComboBox.getSelectedItem();

                int age = Integer.parseInt(ageField.getText());

                int competitorType = 0;

                String selectedType = (String) competitorTypeComboBox.getSelectedItem();
                if ("VoleyballIndoors".equals(selectedType)) {
                    competitorType = 1;} 
                else if ("VolleyballOutdoors".equals(selectedType)) {
                    competitorType = 2;} 
                
                int competitorNumber = competitorList.getHighestID() + 1;
                manager.registerCompetitorLate(competitorType, competitorNumber, name, country, gender, 0, 0, 0, 0, skillLevel, age);

                JOptionPane.showMessageDialog(registerFrame, "Competitor registered successfully");

                launchCompetitorInterface(manager.getCompetitorById(competitorNumber));
                registerFrame.dispose();
            }
        });

        registerFrame.setVisible(true);
    }


    private void handleViewCompetitionButtonClick() {
                JOptionPane.showMessageDialog(frame, "View Competition functionality not implemented.");
    }
}