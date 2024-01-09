import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffInterface {
    private JFrame frame;
    private JButton addScoreButton;
    private JButton viewCompetitorButton;
    private JButton viewCompetitionButton;
    private JButton logOutButton;
    public Manager manager;
    private Staff staff;

    public StaffInterface(Staff staff, Manager manager) {
        this.staff = staff;
        this.manager = manager;
        initialize();
    }

    private void initialize() {
        frame = new JFrame(staff.getName() + " Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addScoreButton = new JButton("Add Score");
        viewCompetitorButton = new JButton("View Competitor");
        viewCompetitionButton = new JButton("View Competition");
        logOutButton = new JButton("Log Out");

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(addScoreButton);
        panel.add(viewCompetitorButton);
        panel.add(viewCompetitionButton);
        panel.add(logOutButton);

        frame.add(panel);

        addScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddScoreButtonClick();
            }
        });

        viewCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewCompetitorButtonClick();
            }
        });

        viewCompetitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewCompetitionButtonClick();
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
        String competitorIDString = JOptionPane.showInputDialog(frame, "Enter Competitor ID:");
        if (competitorIDString != null && !competitorIDString.isEmpty()) {
            try {
                int competitorID = Integer.parseInt(competitorIDString);
                String scoreString = JOptionPane.showInputDialog(frame, "Enter Score:");
                if (scoreString != null && !scoreString.isEmpty()) {
                    try {
                        int score = Integer.parseInt(scoreString);
                        String scoreIndexString = JOptionPane.showInputDialog(frame, "Enter Score Index (1-4):");
                        if (scoreIndexString != null && !scoreIndexString.isEmpty()) {
                            try {
                                int scoreIndex = Integer.parseInt(scoreIndexString);
                                String result = manager.addScore(competitorID, score, scoreIndex);
                                JOptionPane.showMessageDialog(frame, result);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(frame, "Invalid score index. Please enter a valid number.");
                            }
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid score. Please enter a valid number.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid competitor ID. Please enter a valid number.");
            }
        }
    }

    private void handleViewCompetitorButtonClick() {
        String competitorIDString = JOptionPane.showInputDialog(frame, "Enter Competitor ID:");
        if (competitorIDString != null && !competitorIDString.isEmpty()) {
            try {
                int competitorID = Integer.parseInt(competitorIDString);
                Competitor competitor = manager.getCompetitorById(competitorID);
                if (competitor != null) {
                    JOptionPane.showMessageDialog(frame, manager.getCompetitorFullDetail(competitor));
                } else {
                    JOptionPane.showMessageDialog(frame, "Competitor not found with ID: " + competitorID);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid competitor ID. Please enter a valid number.");
            }
        }
    }

    private void handleViewCompetitionButtonClick() {
        String competitionName = JOptionPane.showInputDialog(frame, "Enter Competition Name:");
        if (competitionName != null && !competitionName.isEmpty()) {
            String competitionDetails = manager.ViewCompetition(competitionName);
            JOptionPane.showMessageDialog(frame, competitionDetails);
        }
    }

    private void handleLogOutButtonClick() {
        manager.saveInformationToFile(manager.getCompetitorList());
        frame.dispose();
    }
}