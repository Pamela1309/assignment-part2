import java.util.ArrayList;
import java.util.List;

class Staff {
    private int staffId;
    private Name name;
    private String permissionLevel;

    private static List<Competition> allCompetitions = new ArrayList<>();

    public Staff(int staffId, String firstName, String middleName, String lastName) {
        this.staffId = staffId;
        this.name = new Name(firstName, middleName, lastName);
        this.permissionLevel = "Staff";
    }

    public Staff(int staffId2, Name name2, String string, String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public void viewAllCompetitors(List<Competitor> allCompetitors) {
        System.out.println("All Competitors:");
        for (Competitor competitor : allCompetitors) {
            System.out.println(competitor.getCompetitorNumber() + " - " + competitor.getName());
        }
    }

    public static void setAllCompetitions(List<Competition> competitions) {
        allCompetitions = competitions;
    }

    public static List<Competition> getAllCompetitions() {
        return allCompetitions;
    }

    public int getStaffId() {
        return staffId;
    }

    public Name getName() {
        return name;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void createNewCompetition() {
    }
}