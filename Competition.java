import java.util.Date;

public class Competition {
    public String competitionName;
    public int competitionID;
    public Date date;
    public String category;

    public CompetitorList compList;
    public ScoreList scores;

    public Competition(String compName, int compID, Date date, String cat){
        this.competitionName = compName;
        this.competitionID = compID;
        this.date = date;
        this.category = cat;
        this.compList = new CompetitorList();
        this.scores = new ScoreList();
    }

    public String getCompetitionName(){return competitionName;}
    public int getCompetitionID(){return competitionID;}
    public Date getDate(){return date;}
    public String getCategory(){return category;}    
    public CompetitorList getCompetitorList(){return compList;}
    public ScoreList getScores(){return scores;}

    public void setCompetitionName(String x){this.competitionName = x;}
    public void setCompetitionID(int x){this.competitionID = x;}
    public void setDate(Date x){this.date = x;}
    public void setCategory(String x){this.category = x;}


    public void addScores(String c){    
        Score score = new Score()
        this.scores.add(c);
        (1,2,3,4,5)

    }
}
