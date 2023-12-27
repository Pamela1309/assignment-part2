import java.util.List;
import java.util.Scanner;

class Official extends Staff {

    public String permissionLevel;
    private static Scanner scanner = new Scanner(System.in);

    public Official(int staffId, String firstName, String middleName, String lastName) {
        super(staffId, firstName, middleName, lastName);
        this.permissionLevel = "Official";
    }

    public void removeCompetitor(Competitor competitor, List<Competition> allCompetitions) {
        for (Competition competition : allCompetitions) {
            competition.removeCompetitor(competitor);
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

        System.out.print("Choose competitor type (1. Surfer, 2. Skater): ");
        int competitorType = scanner.nextInt();

        return createCompetitor(competitorID ,competitorType, name, country);
    }

    private Competitor createCompetitor(int competitorID, int competitorType, String name, String country) {
        switch (competitorType) {
            case 1:
                return new Surfer(competitorID, name, country, "Novice", 18);
            case 2:
                return new Skater(competitorID, name, country, "Novice", 18);
            default:
                System.out.println("Invalid competitor type. Try again");
                return registerCompetitor();
        }
    }
    
}