public abstract class Competitor {
    private int competitorNumber;
    private String name;
    private String country;
    private int score1;
    private int score2;
    private int score3;
    private int score4;
    private String gender;
    

    public Competitor(int competitorNumber, String name, String country, String gender, int score1, int score2, int score3, int score4) {
        this.competitorNumber = competitorNumber;
        this.name = name;
        this.country = country;
        this.gender = gender;
        this.score1 = score1;
        this.score2 = score2; 
        this.score3 = score3;
        this.score4 = score4;
    }

    public int getScore1(){
        return score1;
    }
    public void setScore1(int score){
        this.score1 = score;
    }
    public void setScore2(int score){
        this.score2 = score;
    }
    public void setScore3(int score){
        this.score3 = score;
    }
    public void setScore4(int score){
        this.score4 = score;
    }

    public int getScore2(){
        return score2;
    }
    public int getScore3(){
        return score3;
    }
    public int getScore4(){
        return score4;
    }

    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }

    public int getCompetitorNumber() {
        return competitorNumber;
    }

    public void setCompetitorNumber(int competitorNumber) {
        this.competitorNumber = competitorNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getTotalScores(){
        return score1 + score2 + score3 + score4;
    }
    public String getAllScores(){
        return score1 + ", " + score2 + ", " + score3 + ", "+ score4;
    }

    public double getAverageScore() {
        return getTotalScores() / 4.0;
    }
    public String getFullDetails() {
       String s = "Competitor number: " + competitorNumber + "\nName: " + name + " Country: " + country + "\n" +  name +" is a " + gender + " aged " + getAge() + " and has an overall score of: " + getOverallScore();
       return s;
    }

    public int getMaxIndividualScore() {
        return Math.max(Math.max(score1, score2), Math.max(score3, score4));
    }

    public int getMinIndividualScore() {
        return Math.min(Math.min(score1, score2), Math.min(score3, score4));
    }

    public String getShortDetails() {
        String s = "Competitor number: " + competitorNumber + "\nName: " + name + " Country: " + country;
        return s;
    }

    public abstract int getAge();
    public abstract int getOverallScore();
    public abstract String getLevel(); 
    public abstract String getInitials();

    public void setLevel(String newlevel){
    }

    public void setAge(int newage) {
    }
}