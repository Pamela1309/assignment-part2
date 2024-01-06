import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Manager {
    private static Scanner scanner = new Scanner(System.in);
    private CompetitorList competitorList = new CompetitorList();
    private StaffList staffList = new StaffList();
    private CompetitionList competitionList = new CompetitionList();

    public static void main(String[] args) {
        Manager manager = new Manager();  
        manager.runProgram();  
    }

    private void viewAllCompetitions() {System.out.println(competitionList.toString());}

    private void login(CompetitorList competitorList, StaffList staffList) {
        System.out.print("Enter your ID number: ");
        int id = scanner.nextInt();

        Competitor competitor = competitorList.findCompetitorById(id);
        Staff staff = staffList.findStaffById(id);

        if (competitor != null) {
            System.out.println("Logged in as: " + competitor.getName());
            launchCompetitorInterface(competitionList ,competitor, competitorList);
        } 
        else if (staff != null) {
            if (staff.getPermissionLevel().equals("Official")) {
                launchOfficialInterface((Official) staff, competitorList, staffList);
            } else {
                System.out.println("Logged in as: " + staff.getName());
                launchStaffInterface(staff, competitorList, staffList);
                // Additional actions for logged-in user can be added here
            }
        } else {
            System.out.println("No matching ID found.");
        }
    }


    public static Competitor createCompetitor() {
        try{
        System.out.println("Creating a new competitor:");

        System.out.print("Enter competitor number: ");
        int competitorNumber = scanner.nextInt();

        System.out.print("Enter competitor name: ");
        String name = scanner.next();
        name += scanner.next();

        System.out.print("Enter competitor country: ");
        String country = scanner.next();

        System.out.print("Enter competitor gender: ");
        String gender = scanner.next();

        System.out.print("Enter competitor Level: ");
        String level = scanner.next();

        System.out.print("Enter competitor age: ");
        int age = scanner.nextInt();

        // Input scores

        int competitorType;
        do {
            System.out.println("Choose competitor type:");
            System.out.println("1. VolleyballIndoors");
            System.out.println("2. VolleyballOutdoors");
            competitorType = scanner.nextInt();
        } while (competitorType != 1 && competitorType != 2);
                                     
        return createCompetitor(competitorType, competitorNumber, name, country, gender, 0, 0, 0, 0, level, age);
    } catch(java.util.InputMismatchException e) {
        System.out.println("Invalid input. Please enter a Numerical value.");
        scanner.next(); 
        return null;}
}

    private static Competitor createCompetitor(int competitorType, int competitorNumber, String name, String country, String gender,
                                               int score1, int score2, int score3, int score4, String level, int age) {
        switch (competitorType) {
            case 1:
                return new VolleyballIndoors(competitorNumber, name, country, gender, score1, score2, score3, score4, level, age);
            case 2:
                return new VolleyballOutdoors(competitorNumber, name, country, gender, score1, score2, score3, score4, level, age);
            default:
                System.out.println("Invalid competitor type.");
                return new VolleyballIndoors(competitorNumber, name, country, gender, score1, score2, score3, score4, level, age);
    }
}

    private static void buildCompetitions(CompetitionList competitionList){
        Competition IndoorsComp = new Competition("StateChampionship", "Stadium", "05/05/2024");
        competitionList.addToCompetitionList(IndoorsComp);
        Competition OutdoorsComp = new Competition("RegionalChampionship", "TheOval", "16/03/2024");
        competitionList.addToCompetitionList(OutdoorsComp);
    }

    private static void buildCompetitorsFromFile(CompetitorList competitorList, CompetitionList competitionList) {
        try (BufferedReader br = new BufferedReader(new FileReader("SkateBoardingCompetition/src/CompetitorData.txt"))) {
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

                if (type.equals("VolleyballOutdoors")) {
                    competitor = new VolleyballOutdoors(compNum, name, country, gender,
                            Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]),
                            Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]), "Amateur", age);
                    competitionList.findCompetitionByName("StateChampionship").addCompetitor(competitor);
                } else if (type.equals("VolleyballIndoors")) {
                    competitor = new VolleyballIndoors(compNum, name, country, gender,
                            Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]),
                            Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]), "Amateur", age);
                    competitionList.findCompetitionByName("RegionalChampionship").addCompetitor(competitor);
                } else {
                    System.out.println("Invalid competitor type: " + type);
                    continue; 
                }

                competitorList.addCompetitor(competitor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void buildStaff(StaffList staffList){
        staffList.addStaff(new Official(201, "Sarah", "Marie","Thompson"));
        staffList.addStaff(new Staff(202, "James", "", "Turner"));
        staffList.addStaff(new Staff(203, "Maya", "Elizabeth", "Chen"));
        staffList.addStaff(new Staff(204, "Emily", "", "Rodriguez"));
        staffList.addStaff(new Staff(205, "Benjamin", "", "Foster"));
    }

    private static void launchRegistrationInterface(CompetitorList competitorList, CompetitionList competitionList){
            System.out.println("\n" + //
                    "Registration process:");
    
            Competitor competitor = createCompetitor();
            competitorList.addCompetitor(competitor);
    
            System.out.println("Registration successful!");
            launchCompetitorInterface(competitionList ,competitor, competitorList);
        }

    private static void launchCompetitorInterface(CompetitionList competitionList ,Competitor competitor, CompetitorList competitorList) {
        int choice;
        do {
            System.out.println("\n" + 
                    "Choose an option:");
            System.out.println("1. View Competition");
            System.out.println("2. Competitor Profile");
            System.out.println("3. View summary");
            System.out.println("4. Check Scores");
            System.out.println("5. Log Out.");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("\n");
                    System.out.println("Enter competition name:");
                    String comp = scanner.next();
                    System.out.println(competitionList.getCompetitionString(competitionList.findCompetitionByName(comp)));
                    break;
                case 2:
                    System.out.print("\n");
                    System.out.println(competitor.getFullDetails());
                    break;
                case 3:
                System.out.print("\n");
                    System.out.println(competitor.getShortDetails());
                    break;
                case 4:
                System.out.print("\n");
                    System.out.println(competitor.getAllScores());
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }while (choice != 5);
    }

    private static void launchStaffInterface(Staff staff, CompetitorList competitorList, StaffList staffList) {
        int choice;
        do {
            System.out.println("\n" + 
                    "Choose an option:");
            System.out.println("1. Add Score");
            System.out.println("2. View Competitor");
            System.out.println("3. View Competition");
            System.out.println("4. Log Out.");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    staff.createNewCompetition();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    private void launchOfficialInterface(Official staff, CompetitorList competitorList, StaffList staffList) {
        int choice;
        do {
            System.out.println("\n" + //
                    "Choose an option:");
            System.out.println("1. Add Score");
            System.out.println("2. Register Competitor Late");
            System.out.println("3. Remove Competitor");
            System.out.println("4. View Competitor Information");
            System.out.println("5. View Competition Details");
            System.out.println("6. View All Competitors");
            System.out.println("7. Ammend Competitor Details");
            System.out.println("8. Log Out.");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter ID of the Competitor:");
                    int ID = scanner.nextInt();
                    Competitor comp = competitorList.findCompetitorById(ID);
                    System.out.print("Enter the score value: ");
                    int score = scanner.nextInt();
                    System.out.print("Enter the Score index: ");
                    int Temp = scanner.nextInt();
                    switch (Temp) {
                        case 1:
                            comp.setScore1(score);
                            break;
                        case 2:
                            comp.setScore2(score);
                            break;
                        case 3:
                            comp.setScore3(score);
                            break;
                        case 4:
                            comp.setScore4(score);
                            break;
                        default: 
                            break;
                    }
                    break;
                case 2:
                    staff.registerCompetitor();
                    break;
                case 3:
                    System.out.println("Please enter the ID number of the Competitor you would like to remove: ");
                    int id = scanner.nextInt();
                    staff.removeCompetitor(competitorList, id);
                    break;
                case 4:
                    System.out.println("Enter the id of the Competitor you would like to view:");
                    int cmp = scanner.nextInt();
                    System.out.println(competitorList.findCompetitorById(cmp).getFullDetails());    
                break;
                case 5:
                    System.out.print("\n");
                    System.out.println("Enter competition name:");
                    String cmp2 = scanner.next();
                    System.out.println(competitionList.getCompetitionString(competitionList.findCompetitionByName(cmp2)));
                    break;
                case 6:
                    System.out.println("Retrieving All Competitor Profiles . . . ");
                    System.out.println(competitorList.displayCompetitorsById());
                    break;
                case 7:
                    System.out.println("Please enter the ID Number of the Competitor whos Details you would like to change:");
                    int IDD = scanner.nextInt();
                    Competitor compet = competitorList.findCompetitorById(IDD);

                    System.out.println("Please enter the Type of Detail of the Competitor you would like to change: ");
                    System.out.println("1. Name");
                    System.out.println("2. Country");
                    System.out.println("3. Gender");
                    System.out.println("4. Age");
                    System.out.println("5. Level");
                    int DetChoice = scanner.nextInt();
                    switch(DetChoice){
                        case 1:
                            System.out.println("Please enter the new Name: ");
                            String newname = scanner.next();
                            compet.setName(newname);
                            break;
                        case 2:
                            System.out.println("Please enter the new Country: ");
                            String newcountry = scanner.next();
                            compet.setCountry(newcountry);
                            break;
                        case 3:
                            System.out.println("Please enter the new Gender: ");
                            String newgender = scanner.next();
                            compet.setGender(newgender);
                            break;
                        case 4:
                            System.out.println("Please enter the new Age: ");
                            int newage = scanner.nextInt();
                            compet.setAge(newage);
                            break;
                        case 5:
                            System.out.println("Please enter the new skill level: ");
                            String newlevel = scanner.next();
                            compet.setLevel(newlevel);
                            break;
                        
                    }
                        break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);}

    private void runProgram() {
        int choice;
        buildCompetitions(competitionList);
        buildCompetitorsFromFile(competitorList, competitionList);
        buildStaff(staffList);

        do {
            try {
                System.out.println("Choose an option:");
                System.out.println("1. Login");
                System.out.println("2. Register for a new competition");
                System.out.println("3. View all competitions");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    login(competitorList, staffList);
                    break;
                case 2:
                    launchRegistrationInterface(competitorList, competitionList);
                    break;
                case 3:
                    viewAllCompetitions();
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
              } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.next();
                choice = 0;}


        } while (choice != 4);
    }
}