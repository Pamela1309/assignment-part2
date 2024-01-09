import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Manager implements UI {
    public static Scanner scanner = new Scanner(System.in);
    public CompetitorList competitorList = new CompetitorList();
    public StaffList staffList = new StaffList();
    public CompetitionList competitionList = new CompetitionList();

    public void init(){
        buildCompetitions(competitionList);
        buildCompetitorsFromFile(competitorList, competitionList);
        buildStaff(staffList);
    }
    public CompetitionList getCompetitionList(){
        return competitionList;
    }
    public CompetitorList getCompetitorList(){
        return competitorList;
    }
    public StaffList getStaffList(){
        return staffList;
    }

    public static void buildCompetitions(CompetitionList competitionList){
        Competition IndoorsComp = new Competition("StateChampionship", "Stadium", "05/05/2024");
        competitionList.addToCompetitionList(IndoorsComp);
        Competition OutodoorsComp = new Competition("RegionalChampionship", "TheOval", "16/03/2024");
        competitionList.addToCompetitionList(OutodoorsComp);
    }

    public static void buildStaff(StaffList staffList){
        staffList.addStaff(new Official(201, "Sarah", "Marie","Thompson"));
        staffList.addStaff(new Staff(202, "James", "", "Turner"));
        staffList.addStaff(new Staff(203, "Maya", "Elizabeth", "Chen"));
        staffList.addStaff(new Staff(204, "Emily", "", "Rodriguez"));
        staffList.addStaff(new Staff(205, "Benjamin", "", "Foster"));
    }

    public void buildCompetitorsFromFile(CompetitorList competitorList, CompetitionList competitionList) {
        try (BufferedReader br = new BufferedReader(new FileReader("CompetitorsInfo.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                int compNum = Integer.parseInt(tokens[0]);
                String name = tokens[1] + " " + tokens[2];
                String type = tokens[3];
                String gender = tokens[5];
                int age = Integer.parseInt(tokens[4]);
                String country = tokens[6];
                Competitor competitor;

                if (type.equals("VolleyballIndoors")) {
                    competitor = new VolleyballIndoors(compNum, name, country, gender,
                            Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]),
                            Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]), "Novice", age);
                    competitionList.findCompetitionByName("StateChampionship").addCompetitor(competitor);
                } else if (type.equals("VolleyballOutdoors")) {
                    competitor = new VolleyballOutdoors(compNum, name, country, gender,
                            Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]),
                            Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]), "Novice", age);
                    competitionList.findCompetitionByName("RegionalChampionship").addCompetitor(competitor);
                } else {
                    System.out.println("Invalid competitor type: " + type);
                    continue; // Skip invalid entries
                }

                competitorList.addCompetitor(competitor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveInformationToFile(CompetitorList competitorList) {
        try (FileWriter writer = new FileWriter("competitor_information.txt")) {
            // Save a table of competitors with full details
            writer.write("Competitors:\n");
            for (Competitor competitor : competitorList.getCompetitors()) {
                writer.write(competitor.getFullDetails() + "\n");
            }

            // Save details of the competitor with the highest overall score
            Competitor maxScoreCompetitor = competitorList.getTop3Contestants().get(0);
            writer.write("\nCompetitor with the highest overall score:\n");
            writer.write(maxScoreCompetitor.getFullDetails() + "\n");

            // Save four other summary statistics (totals, averages, max, min of scores)
            writer.write("\nSummary Statistics:\n");
            writer.write("Total Scores: " + competitorList.getTotalScores() + "\n");
            writer.write("Average Score: " + competitorList.getAverageScore() + "\n");
            writer.write("Max Score: " + competitorList.getMaxScore() + "\n");
            writer.write("Min Score: " + competitorList.getMinScore() + "\n");

            // Save frequency report (individual score awards)
            Map<Integer, Integer> scoreFrequencyMap = competitorList.getScoreFrequency();
            writer.write("\nScore Frequency Report:\n");
            for (Map.Entry<Integer, Integer> entry : scoreFrequencyMap.entrySet()) {
                writer.write("Score " + entry.getKey() + ": " + entry.getValue() + " times\n");
            }

            System.out.println("Information saved to competitor_information.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String addScore(int competitorId, int score, int scoreIndex) {
        Competitor competitor = competitorList.findCompetitorById(competitorId);
    
        if (competitor == null) {
            return "Competitor not found with ID: " + competitorId;
        }
    
        // Assuming scoreIndex is in the range [1, 4]
        switch (scoreIndex) {
            case 1:
                competitor.setScore1(score);
                break;
            case 2:
                competitor.setScore2(score);
                break;
            case 3:
                competitor.setScore3(score);
                break;
            case 4:
                competitor.setScore4(score);
                break;
            default:
                return "Invalid score index. Please provide a value between 1 and 4.";
        }
    
        return "Score added successfully for Competitor ID " + competitorId;
    }

    public String registerCompetitorLate(int competitorType, int competitorNumber, String name, String country, String gender,
                                               int score1, int score2, int score3, int score4, String level, int age) {
        try {
              Competitor competitor = createCompetitor(competitorType, competitorNumber, name, country, gender, score1, score2, score3, score4, level, age);
            if (competitor != null) {
                competitorList.addCompetitor(competitor);
                return "Competitor registered successfully!";
            } else {
                return "Failed to register competitor. Invalid input.";
            }
        } catch (NumberFormatException e) {
            return "Invalid input. Please enter valid competitor details.";
        }
    }

    private static Competitor createCompetitor(int competitorType, int competitorNumber, String name, String country, String gender,
                                               int score1, int score2, int score3, int score4, String level, int age) {
        switch (competitorType) {
            case 1:
                return new VolleyballOutdoors(competitorNumber, name, country, gender, score1, score2, score3, score4, level, age);
            case 2:
                return new VolleyballIndoors(competitorNumber, name, country, gender, score1, score2, score3, score4, level, age);
            default:
                System.out.println("Invalid competitor type.");
                return null;
            }
    }
    public void changeDetails(int ID, int choice, String newval){
    Competitor compet = competitorList.findCompetitorById(ID);
    //System.out.println("1. Name");
    //System.out.println("2. Country");
    //System.out.println("3. Gender");
    //System.out.println("4. Age");
    //System.out.println("5. Level");
    switch(choice){
        case 1:
            compet.setName(newval);
            break;
        case 2:
            compet.setCountry(newval);
            break;
        case 3:
            compet.setGender(newval);
            break;
        case 4:
            compet.setAge(Integer.parseInt(newval));
            break;
        case 5:;
            compet.setLevel(newval);
            break;
        }
    }
    public String getCompetitorFullDetail(Competitor competitor){
        return competitor.getFullDetails();
    }
    public String ViewCompetition(String comp){
    return competitionList.getCompetitionString(competitionList.findCompetitionByName(comp));
    }
    public String viewAllCompetitions(){
        return competitionList.toString();
    }
    public String getShortDetail(Competitor competitor){
        return competitor.getShortDetails();
    }
    public String getAllScores(Competitor competitor){
        return competitor.getAllScores();
    }
    public void removeCompetitor(Official staff, int id){
            staff.removeCompetitor(competitorList, id);
    }
    public String viewAllCompetitors(){
        return competitorList.displayCompetitorsById();
    }
    public Competitor getCompetitorById(int id) {
        for (Competitor competitor : competitorList.getCompetitors()) {
            if (competitor.getCompetitorNumber() == id) {
                return competitor;
            }
        }
        return null; // Competitor not found with the given ID
    }
    public Staff getStaffById(int id) {
        for (Staff staff : staffList.getStaffList()) {
            if (staff.getStaffId() == id) {
                return staff;
            }
        }
        return null; // Staff not found with the given ID
    }
}