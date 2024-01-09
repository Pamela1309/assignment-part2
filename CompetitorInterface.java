import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompetitorInterface {
    private JFrame frame;
    private JButton viewCompetitionButton;
    private JButton viewProfileButton;
    private JButton viewSummaryButton;
    private JButton checkScoresButton;
    private JButton logoutButton;
    public Manager manager = null;

    public CompetitorInterface(Competitor competitor, Manager manager) {
        this.manager = manager;
        frame = new JFrame("Competitor Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        viewCompetitionButton = new JButton("View Competition");
        viewProfileButton = new JButton("View Profile");
        viewSummaryButton = new JButton("View Summary");
        checkScoresButton = new JButton("Check Scores");
        logoutButton = new JButton("Log Out");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(viewCompetitionButton);
        panel.add(viewProfileButton);
        panel.add(viewSummaryButton);
        panel.add(checkScoresButton);
        panel.add(logoutButton);

        frame.add(panel);

        viewCompetitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewCompetitionButtonClick();
            }
        });

        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewProfileButtonClick();
            }
        });

        viewSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewSummaryButtonClick();
            }
        });

        checkScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckScoresButtonClick();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogoutButtonClick();
            }
        });
    }

    public void display() {
        frame.setVisible(true);
    }

    private void handleViewCompetitionButtonClick() {
        String competitionName = JOptionPane.showInputDialog(frame, "Enter the name of the competition:");
        if (competitionName != null && !competitionName.isEmpty()) {
            String competitionDetails = manager.ViewCompetition(competitionName);
            JOptionPane.showMessageDialog(frame, competitionDetails);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid competition name. Please enter a valid name.");
        }
    }

    private void handleViewProfileButtonClick() {
        int competitorId = getCompetitorIdFromUser();
        if (competitorId != -1) {
            Competitor competitor = manager.getCompetitorById(competitorId);
            if (competitor != null) {
                String competitorDetails = manager.getCompetitorFullDetail(competitor);
                JOptionPane.showMessageDialog(frame, competitorDetails);
            } else {
                JOptionPane.showMessageDialog(frame, "Competitor not found with ID: " + competitorId);
            }
        }
    }

    private void handleViewSummaryButtonClick() {
        String summary = manager.viewAllCompetitors();
        JOptionPane.showMessageDialog(frame, summary);
    }

    private void handleCheckScoresButtonClick() {
        int competitorId = getCompetitorIdFromUser();
        if (competitorId != -1) {
            Competitor competitor = manager.getCompetitorById(competitorId);
            if (competitor != null) {
                String allScores = manager.getAllScores(competitor);
                JOptionPane.showMessageDialog(frame, allScores);
            } else {
                JOptionPane.showMessageDialog(frame, "Competitor not found with ID: " + competitorId);
            }
        }
    }

    private int getCompetitorIdFromUser() {
        String competitorID = JOptionPane.showInputDialog(frame, "Enter the ID of the competitor:");
        if (competitorID != null && !competitorID.isEmpty()) {
            return Integer.parseInt(competitorID);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid competitor ID. Please enter a valid ID.");
            return -1;
        }
    }
    private void handleLogoutButtonClick() {
        manager.saveInformationToFile(manager.getCompetitorList());
        frame.dispose();
    }
}