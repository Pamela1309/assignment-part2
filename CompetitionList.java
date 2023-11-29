import java.util.ArrayList;

public class CompetitionList {
    public ArrayList<Staff> allCompetition;

    public CompetitionList(){
        this.allCompetition = new ArrayList<Staff>();
    }

    public void addToCompetitionList(Staff e){
        this.allCompetition.add(e);
    }
}
