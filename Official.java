
import java.util.Scanner;

class Official extends Staff {

    public String permissionLevel;
    private static Scanner scanner = new Scanner(System.in);

    public Official(int staffId, String firstName, String middleName, String lastName) {
        super(staffId, firstName, middleName, lastName);
        this.permissionLevel = "Official";
    }
    @Override
    public String getPermissionLevel(){
        return this.permissionLevel;
    }



    public void removeCompetitor(CompetitorList competitorList ,int ID) {
        Competitor competitor = competitorList.findCompetitorById(ID);
        for (Competition competition : allCompetitions) {
            if(competition.checkForCompetitor(ID)){competition.removeCompetitor(competitor);}
            else{
                System.out.println("The competitor you are searching for does not exist.");
            }
        }
    }

public Competitor registerCompetitor() {
    System.out.println("Registering a new competitor mid-competition:");

    System.out.print("Enter Desired CompetitorNumber: ");
    int competitorID = scanner.nextInt();

    System.out.print("Enter competitor name: ");
    String name = scanner.next();

    System.out.print("Enter competitor country: ");
    String country = scanner.next();

    System.out.print("Enter competitor gender: ");
    String gender = scanner.next();

    System.out.print("Enter competitor age: ");
    int age = scanner.nextInt();

    System.out.print("Enter competitor level: ");
    String level = scanner.next();     

    System.out.print("Choose competitor type (1. VolleyballIndoors, 2. VolleyballOutdoors): ");
    int competitorType = scanner.nextInt();

    return createCompetitor(competitorType, competitorID, name, country, gender, 0, 0, 0, 0, level, age);
}

private Competitor createCompetitor(int competitorType, int competitorID, String name, String country, String gender,
                                    int score1, int score2, int score3, int score4, String level, int age) {
    switch (competitorType) {
        case 1:
            return new VolleyballIndoors(competitorID, name, country, gender, score1, score2, score3, score4, level, age);
        case 2:
            return new VolleyballOutdoors(competitorID, name, country, gender, score1, score2, score3, score4, level, age);
        default:
            System.out.println("Invalid competitor type. Creating a default competitor.");
            return new VolleyballIndoors(competitorID, name, country, gender, score1, score2, score3, score4, level, age);
    }
}
}