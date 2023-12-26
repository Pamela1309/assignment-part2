import java.util.Date;

public class Competition {
    private int competitionID;
    private String name;
    private String location;
    private Date date;
    private CompetitorList competitors = new CompetitorList();

    public Competition(String name, String location, Date date) {
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
    public Date getDate(){return date;}
    public CompetitorList getCompetitorList(){return competitors;}
    public String getLocation(){return location;}


    public void setCompetitionName(String x){this.name = x;}
    public void setCompetitionID(int x){this.competitionID = x;}
    public void setDate(Date x){this.date = x;}
}
