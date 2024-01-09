import java.util.Scanner;

public interface UI {
    public static Scanner scanner = new Scanner(System.in);
    public static Manager manager = new Manager();
    public static CompetitorList competitorList = manager.getCompetitorList();
    public static StaffList staffList = manager.getStaffList();
    public static CompetitionList competitionList = manager.getCompetitionList();
        
    public static void main(String[] args) {
        Manager.buildCompetitions(competitionList);
        manager.buildCompetitorsFromFile(competitorList, competitionList);
        Manager.buildStaff(staffList);
        
        showMainMenu();
    }

    public static void showMainMenu() {
        int choice;
        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. View Competition");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    viewCompetition();
                    break;
                case 4:
                    System.out.println("Exiting..");
                    break;
                default:
                    System.out.println("Invalid. Please try again.");
            }
        } while (choice != 4);
    }

    public static void login() {
        System.out.print("Enter your ID number: ");
        int id = scanner.nextInt();

        Competitor competitor = manager.getCompetitorById(id);
        Staff staff = manager.getStaffById(id);

        if (competitor != null) {
            System.out.println("Logged in as: " + competitor.getName());
            launchCompetitorInterface(competitor);
        } else if (staff != null) {
            System.out.println("Logged in as: " + staff.getName());
            if (staff instanceof Official) {
                launchOfficialInterface((Official) staff, manager);
            } else {
                launchStaffInterface(staff);
            }
        } else {
            System.out.println("No matching ID found.");
        }
    }

    public static void launchCompetitorInterface(Competitor competitor) {
        int choice;
        do {
            System.out.println("\nCompetitor Menu:");
            System.out.println("1. View Competition");
            System.out.println("2. View Competitor Profile");
            System.out.println("3. View Summary");
            System.out.println("4. Check Scores");
            System.out.println("5. Log Out");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewCompetition();
                    break;
                case 2:
                    System.out.println(manager.getCompetitorFullDetail(competitor));
                    break;
                case 3:
                    System.out.println(manager.getShortDetail(competitor));
                    break;
                case 4:
                    System.out.println(manager.getAllScores(competitor));
                    break;
                case 5:
                    System.out.println("Logging out");
                    break;
                default:
                    System.out.println("Invalid. Please try again.");
            }
        } while (choice != 5);
    }

    public static void launchStaffInterface(Staff staff) {
        int choice;
        do {
            System.out.println("\nStaff Menu:");
            System.out.println("1. Add Score");
            System.out.println("2. View Competitor");
            System.out.println("3. View Competition");
            System.out.println("4. Log Out");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addScore(staff);
                    break;
                case 2:
                    viewCompetitor();
                    break;
                case 3:
                    viewCompetition();
                    break;
                case 4:
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid. Please try again.");
            }
        } while (choice != 4);
    }

    public static void launchOfficialInterface(Official official, Manager manager) {
        int choice;
        do {
            System.out.println("\nOfficial Menu:");
            System.out.println("1. Add Score");
            System.out.println("2. Register Competitor Late");
            System.out.println("3. Remove Competitor");
            System.out.println("4. View Competitor Information");
            System.out.println("5. View Competition Details");
            System.out.println("6. View All Competitors");
            System.out.println("7. Amend Competitor Details");
            System.out.println("8. Log Out");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addScore(official);
                    break;
                case 2:
                    registerCompetitorLate();
                    break;
                case 3:
                    removeCompetitor(official);
                    break;
                case 4:
                    viewCompetitor();
                    break;
                case 5:
                    viewCompetition();
                    break;
                case 6:
                    System.out.print(manager.viewAllCompetitors());
                    break;
                case 7:
                    changeDetails();
                    break;
                case 8:
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid. Please try again.");
            }
        } while (choice != 8);
    }

    public static void registerCompetitorLate() {
        
        int competitorNumber = competitorList.getHighestID() + 1;
        System.out.print("Enter Name in the form of 'FirstName Surname': ");
        String name = scanner.next();
        name += scanner.next();
        System.out.print("Enter Country of Origin: ");
        String country = scanner.next();
        System.out.print("Enter your gender: Male/Female ");
        String gender = scanner.next();
        System.out.print("Enter your estimated level - (Novice - Intermediate - Expert) ");
        String level = scanner.next();
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        System.out.print("Select Type of competitor: 1-VolleyballIndoors 2-VolleyballOutdoors ");
        int competitorType = scanner.nextInt();
        manager.registerCompetitorLate(competitorType, competitorNumber, name, country, gender, 0, 0, 0, 0, level, age);
        System.out.print("Your login number is: " + competitorNumber);
    }

    public static void removeCompetitor(Official official) {
        System.out.print("Enter the ID of the Competitor: ");
        int id = scanner.nextInt();
        manager.removeCompetitor(official, id);
    }

    public static void register() {
        registerCompetitorLate();
    }

    public static void viewCompetition() {
        System.out.print("Enter competition name: ");
        String comp = scanner.next();
        System.out.println(manager.ViewCompetition(comp));
    }

    public static void viewCompetitor() {
        System.out.print("Enter the ID of the Competitor you would like to view: ");
        int cmp = scanner.nextInt();
        System.out.println(manager.getCompetitorFullDetail(manager.getCompetitorById(cmp)));
    }

    public static void changeDetails(){
        System.out.print("Enter the ID of the Competitor: ");
        int ID = scanner.nextInt();
        System.out.print("Enter the Details that need to be changed: ");
        System.out.println("1. Name");
        System.out.println("2. Country");
        System.out.println("3. Gender");
        System.out.println("4. Age");
        System.out.println("5. Level");
        int choice = scanner.nextInt();
        System.out.print("Enter the new value: ");
        String newval = scanner.next();
        manager.changeDetails(ID, choice, newval);
        System.out.print("Changes have been made");
    }

    public static void addScore(Staff staff) {
        System.out.print("Enter ID of the Competitor: ");
        int ID = scanner.nextInt();
        System.out.print("Enter the score index: ");
        int index = scanner.nextInt();
        System.out.print("Enter the Score: ");
        int score = scanner.nextInt();
        manager.addScore(ID, index, score);
    }
}