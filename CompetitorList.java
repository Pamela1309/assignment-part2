import java.util.ArrayList;

public class CompetitorList {
    private ArrayList<Competitor> competitorList;

    public CompetitorList() {
        this.competitorList = new ArrayList<>();
    }

    public void addCompetitor(Competitor competitor) {
        competitorList.add(competitor);
    }

    public Competitor findCompetitorById(int competitorNumber) {
        for (Competitor competitor : competitorList) {
            if (competitor.getCompetitorNumber() == competitorNumber) {
                return competitor;
            }
        }
        return null; // Return null if no match is found
    }

    public void add(Competitor competitor) {
        competitorList.add(competitor);
    }

    public void remove(Competitor competitor) {
        competitorList.remove(competitor);
    }

}