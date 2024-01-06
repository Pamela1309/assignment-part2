import java.util.ArrayList;

public class CompetitionList {
    
    public ArrayList<Competition> allCompetition;

    public CompetitionList(){
        this.allCompetition = new ArrayList<Competition>();
    }

    public void addToCompetitionList(Competition c){
        this.allCompetition.add(c);

    }
    public Competition findCompetitionByName(String name){
        for (Competition competition : allCompetition) {
            if (competition.getCompetitionName().equals(name)) {
                return competition;
            }
        }
        return null;
    }

    public String getCompetitionString(Competition comp) {
        return comp.getCompetitionSummary();
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Competitions:\n");

        for (Competition competition : allCompetition) {
            sb.append("Name: ").append(competition.getCompetitionName())
                    .append(", Location: ").append(competition.getLocation())
                    .append(", Date: ").append(competition.getDate())
                    .append("\n");
        }
        return sb.toString();
    }
}