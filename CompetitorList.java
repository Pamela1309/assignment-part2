import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitorList {
    private ArrayList<Competitor> competitorList;

    public CompetitorList() {
        this.competitorList = new ArrayList<>();
    }

    public ArrayList<Competitor> getCompetitors() {
        return competitorList;
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

    public List<Competitor> getTop3Contestants() {
        return competitorList.stream().sorted(Comparator.comparingDouble(Competitor::getAverageScore).reversed()).limit(3).collect(Collectors.toList());
            }

    public String displayCompetitorsById() {
        StringBuilder sb = new StringBuilder();
        sb.append("Competitors sorted by ID:\n");
            
        competitorList.stream().sorted(Comparator.comparingInt(Competitor::getCompetitorNumber)).forEach(competitor -> sb.append(competitor.getFullDetails()).append("\n"));
            
                return sb.toString();
            }

}