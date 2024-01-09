import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OfficialInterface {
    private JFrame frame;
    private JButton addScoreButton;
    private JButton registerCompetitorLateButton;
    private JButton removeCompetitorButton;
    private JButton viewCompetitorInformationButton;
    private JButton viewCompetitionDetailsButton;
    private JButton viewAllCompetitorsButton;
    private JButton amendCompetitorDetailsButton;
    private JButton logOutButton;
    public Manager manager;
    public Official official;

    public OfficialInterface(Official official, Manager manager) {
        this.manager = manager;
        this.official = official;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Official Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addScoreButton = new JButton("Add Score");
        registerCompetitorLateButton = new JButton("Register Competitor Late");
        removeCompetitorButton = new JButton("Remove Competitor");
        viewCompetitorInformationButton = new JButton("View Competitor Information");
        viewCompetitionDetailsButton = new JButton("View Competition Details");
        viewAllCompetitorsButton = new JButton("View All Competitors");
        amendCompetitorDetailsButton = new JButton("Amend Competitor Details");
        logOutButton = new JButton("Log Out");

        JPanel panel = new JPanel(new GridLayout(8, 1));
        panel.add(addScoreButton);
        panel.add(registerCompetitorLateButton);
        panel.add(removeCompetitorButton);
        panel.add(viewCompetitorInformationButton);
        panel.add(viewCompetitionDetailsButton);
        panel.add(viewAllCompetitorsButton);
        panel.add(amendCompetitorDetailsButton);
        panel.add(logOutButton);

        frame.add(panel);

        addScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddScoreButtonClick();
            }
        });

        registerCompetitorLateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegisterCompetitorLateButtonClick();
            }
        });

        removeCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRemoveCompetitorButtonClick();
            }
        });

        viewCompetitorInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewCompetitorInformationButtonClick();
            }
        });

        viewCompetitionDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewCompetitionDetailsButtonClick();
            }
        });

        viewAllCompetitorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewAllCompetitorsButtonClick();
            }
        });

        amendCompetitorDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAmendCompetitorDetailsButtonClick();
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogOutButtonClick();
            }
        });
    }

    public void display() {
        frame.setVisible(true);
    }

    private void handleAddScoreButtonClick() {
        String competitorIdStr = JOptionPane.showInputDialog(frame, "Enter competitor ID:");
        if (competitorIdStr != null && !competitorIdStr.isEmpty()) {
            int competitorId = Integer.parseInt(competitorIdStr);
            Competitor competitor = manager.getCompetitorById(competitorId);
            if (competitor != null) {
                String scoreStr = JOptionPane.showInputDialog(frame, "Enter score:");
                if (scoreStr != null && !scoreStr.isEmpty()) {
                    int score = Integer.parseInt(scoreStr);
                    String scoreIndexStr = JOptionPane.showInputDialog(frame, "Enter score index (1-4):");
                    if (scoreIndexStr != null && !scoreIndexStr.isEmpty()) {
                        int scoreIndex = Integer.parseInt(scoreIndexStr);
                        String result = manager.addScore(competitorId, score, scoreIndex);
                        JOptionPane.showMessageDialog(frame, result);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid score index. Please enter a value between 1 and 4.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid score. Please enter a valid score.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Competitor not found with ID: " + competitorId);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid competitor ID. Please enter a valid ID.");
        }
    }

    private void handleRegisterCompetitorLateButtonClick() {
        String competitorTypeStr = JOptionPane.showInputDialog(frame, "Enter competitor type (1 for VolleyballIndoors, 2 for VolleyballOutdoors):");
        if (competitorTypeStr != null && !competitorTypeStr.isEmpty()) {
            int competitorType = Integer.parseInt(competitorTypeStr);
            String competitorNumberStr = JOptionPane.showInputDialog(frame, "Enter competitor number:");
            if (competitorNumberStr != null && !competitorNumberStr.isEmpty()) {
                int competitorNumber = Integer.parseInt(competitorNumberStr);
                String name = JOptionPane.showInputDialog(frame, "Enter name:");
                String country = JOptionPane.showInputDialog(frame, "Enter country:");
                String gender = JOptionPane.showInputDialog(frame, "Enter gender:");
                String score1Str = JOptionPane.showInputDialog(frame, "Enter score 1:");
                String score2Str = JOptionPane.showInputDialog(frame, "Enter score 2:");
                String score3Str = JOptionPane.showInputDialog(frame, "Enter score 3:");
                String score4Str = JOptionPane.showInputDialog(frame, "Enter score 4:");
                String level = JOptionPane.showInputDialog(frame, "Enter level:");
                String ageStr = JOptionPane.showInputDialog(frame, "Enter age:");
                if (score1Str != null && !score1Str.isEmpty() &&
                        score2Str != null && !score2Str.isEmpty() &&
                        score3Str != null && !score3Str.isEmpty() &&
                        score4Str != null && !score4Str.isEmpty() &&
                        ageStr != null && !ageStr.isEmpty()) {
                    int score1 = Integer.parseInt(score1Str);
                    int score2 = Integer.parseInt(score2Str);
                    int score3 = Integer.parseInt(score3Str);
                    int score4 = Integer.parseInt(score4Str);
                    int age = Integer.parseInt(ageStr);

                    String result = manager.registerCompetitorLate(competitorType, competitorNumber, name, country, gender, score1, score2, score3, score4, level, age);
                    JOptionPane.showMessageDialog(frame, result);

                    Competitor competitor = manager.getCompetitorById(competitorNumber);
                    if (competitor != null) {
                        String confirmation = "Competitor registered successfully!\nCompetitor ID: " + competitor.getCompetitorNumber();
                        JOptionPane.showMessageDialog(frame, confirmation);
                        handleViewCompetitorInformationButtonClick();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to register competitor.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid competitor details.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid competitor number. Please enter a valid number.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid competitor type. Please enter 1 for VolleyballIndoors or 2 for VolleyballOutdoors.");
        }
    }

    private void handleRemoveCompetitorButtonClick() {
        String competitorIdStr = JOptionPane.showInputDialog(frame, "Enter competitor ID:");
        if (competitorIdStr != null && !competitorIdStr.isEmpty()) {
            int competitorId = Integer.parseInt(competitorIdStr);
            Competitor competitor = manager.getCompetitorById(competitorId);
            if (competitor != null) {
                manager.removeCompetitor(official, competitorId);
                JOptionPane.showMessageDialog(frame, "Competitor removed successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Staff not found with ID: " + competitorId);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid competitor ID. Please enter a valid ID.");
        }
    }

    private void handleViewCompetitorInformationButtonClick() {
        String competitorIdStr = JOptionPane.showInputDialog(frame, "Enter competitor ID:");
        if (competitorIdStr != null && !competitorIdStr.isEmpty()) {
            int competitorId = Integer.parseInt(competitorIdStr);
            Competitor competitor = manager.getCompetitorById(competitorId);
            if (competitor != null) {
                String competitorInfo = manager.getCompetitorFullDetail(competitor);
                JOptionPane.showMessageDialog(frame, competitorInfo);
            } else {
                JOptionPane.showMessageDialog(frame, "Competitor not found with ID: " + competitorId);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid competitor ID. Please enter a valid ID.");
        }
    }

    private void handleViewCompetitionDetailsButtonClick() {
        String competitionName = JOptionPane.showInputDialog(frame, "Enter competition name:");
        if (competitionName != null && !competitionName.isEmpty()) {
            String competitionDetails = manager.ViewCompetition(competitionName);
            JOptionPane.showMessageDialog(frame, competitionDetails);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid competition name. Please enter a valid name.");
        }
    }

    private void handleViewAllCompetitorsButtonClick() {
        String allCompetitors = manager.viewAllCompetitors();
        JOptionPane.showMessageDialog(frame, allCompetitors);
    }

    private void handleAmendCompetitorDetailsButtonClick() {
        String competitorIdStr = JOptionPane.showInputDialog(frame, "Enter competitor ID:");
        if (competitorIdStr != null && !competitorIdStr.isEmpty()) {
            int competitorId = Integer.parseInt(competitorIdStr);
            Competitor competitor = manager.getCompetitorById(competitorId);
            if (competitor != null) {
                String choices = "1. Name\n2. Country\n3. Gender\n4. Age\n5. Level";
                String choiceStr = JOptionPane.showInputDialog(frame, "Choose what to change:\n" + choices);
                if (choiceStr != null && !choiceStr.isEmpty()) {
                    int choice = Integer.parseInt(choiceStr);
                    String newVal = JOptionPane.showInputDialog(frame, "Enter new value:");
                    manager.changeDetails(competitorId, choice, newVal);
                    JOptionPane.showMessageDialog(frame, "Details changed successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid choice. Please enter a valid choice.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Competitor not found with ID: " + competitorId);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid competitor ID. Please enter a valid ID.");
        }
    }

    private void handleLogOutButtonClick() {
        manager.saveInformationToFile(manager.getCompetitorList());
        frame.dispose();
    }
}