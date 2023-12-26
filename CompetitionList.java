import java.util.ArrayList;

public class CompetitionList {
    
    public ArrayList<Competition> allCompetition;

    public CompetitionList(){
        this.allCompetition = new ArrayList<Competition>();
    }

    public void addToCompetitionList(Competition c){
        this.allCompetition.add(c);

    }
}