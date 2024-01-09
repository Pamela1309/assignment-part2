import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public void add(Competitor competitor){
        competitorList.add(competitor);
    }

    public Competitor findCompetitorById(int competitorNumber) {
        for (Competitor competitor : competitorList) {
            if (competitor.getCompetitorNumber() == competitorNumber) {
                return competitor;
            }
        }
        return null;
    }

    public void remove(Competitor competitor) {
        competitorList.remove(competitor);
    }

    public int getHighestID() {
        if (competitorList.isEmpty()) {
            return -1; // Return -1 if the list is empty
        }

        return competitorList.stream()
                .max(Comparator.comparingInt(Competitor::getCompetitorNumber))
                .map(Competitor::getCompetitorNumber)
                .orElse(-1);
    }

    public String getMinScore() {
        if (competitorList.isEmpty()) {
            return "No competitors in the list";
        }

        Competitor minScoreCompetitor = competitorList.stream()
                .min(Comparator.comparingInt(Competitor::getMinIndividualScore))
                .orElse(null);

        if (minScoreCompetitor != null) {
            return "Competitor with the lowest individual score: " + minScoreCompetitor.getName();
        } else {
            return "Unable to determine competitor with the lowest individual score";
        }
    }

    public Map<Integer, Integer> getScoreFrequency() {
        Map<Integer, Integer> scoreFrequencyMap = new HashMap<>();

        for (Competitor competitor : competitorList) {
            int[] individualScores = {competitor.getScore1(), competitor.getScore2(), competitor.getScore3(), competitor.getScore4()};

            for (int score : individualScores) {
                scoreFrequencyMap.put(score, scoreFrequencyMap.getOrDefault(score, 0) + 1);
            }
        }

        return scoreFrequencyMap;
    }

    public String getMaxScore() {
        if (competitorList.isEmpty()) {
            return "No competitors in the list";
        }

        Competitor maxScoreCompetitor = competitorList.stream()
                .max(Comparator.comparingInt(Competitor::getMaxIndividualScore))
                .orElse(null);

        if (maxScoreCompetitor != null) {
            return "Competitor with the highest individual score: " + maxScoreCompetitor.getName();
        } else {
            return "Unable to determine competitor with the highest individual score";
        }
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

    public int getTotalScores() {
        int totalScores = 0;

        for (Competitor competitor : competitorList) {
            totalScores += competitor.getTotalScores();
        }

        return totalScores;
    }

    public double getAverageScore() {
        if (competitorList.isEmpty()) {
            return 0.0; // Avoid division by zero
        }

        int totalScores = getTotalScores();
        return (double) totalScores / competitorList.size();
    }
}