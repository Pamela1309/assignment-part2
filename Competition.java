import java.util.List;

public class Competition {
    private int competitionID;
    private String name;
    private String location;
    private String date;
    private CompetitorList competitors = new CompetitorList();

    public Competition(String name, String location, String date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public CompetitorList getCompetitors() {
        return competitors;
    }

    public void addCompetitor(Competitor competitor) {
        competitors.add(competitor);
    }

    public void removeCompetitor(Competitor competitor) {
        competitors.remove(competitor);
    }

    public String getCompetitionName(){return name;}
    public int getCompetitionID(){return competitionID;}
    public String getDate(){return date;}
    public CompetitorList getCompetitorList(){return competitors;}
    public String getLocation(){return location;}

    public void setCompetitionName(String x){this.name = x;}
    public void setCompetitionID(int x){this.competitionID = x;}
    public void setDate(String x){this.date = x;}

    public boolean checkForCompetitor(int iD) {
        for (Competitor competitor : competitors.getCompetitors()) {
            if (competitor.getCompetitorNumber() == iD) {
                return true;
            }
        }
        return false;
    }


    public String getCompetitionSummary() {
        StringBuilder summary = new StringBuilder();

        summary.append("Competition ID: ").append(competitionID).append("\n");
        summary.append("Competition Name: ").append(name).append("\n");
        summary.append("Location: ").append(location).append("\n");
        summary.append("Date: ").append(date).append("\n");

        // Append top 3 contestants
        List<Competitor> topContestants = competitors.getTop3Contestants(); 
        summary.append("Top 3 Contestants:\n");
        for (int i = 0; i < topContestants.size(); i++) {
            summary.append("Rank ").append(i + 1).append(": ").append(topContestants.get(i).getName()).append("\n");
        }
        return summary.toString();
    }


    

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Competition ID: ").append(competitionID).append("\n");
        sb.append("Competition Name: ").append(name).append("\n");
        sb.append("Location: ").append(location).append("\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("Competitors:\n");
    
        for (Competitor competitor : competitors.getCompetitors()) {
            sb.append(competitor.toString()).append("\n");
        }
    
        return sb.toString();
    }

}