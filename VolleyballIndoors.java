public class VolleyballIndoors extends Competitor {
    private String level;
    private int age;

    public VolleyballIndoors(int competitorNumber, String name, String country, String gender, int score1, int score2, int score3, int score4, String level, int age){
        super(competitorNumber, name, country, gender, score1, score2, score3, score4);
        this.level = level;
        this.age = age;
    }

    @Override
    public int getOverallScore() {
        return getScore1() + getScore2() + getScore3() + getScore4();
    }

    @Override
    public void setLevel(String newlevel) {
        this.level = newlevel;
    }
    @Override
    public void setAge(int newage) {
        this.age = newage;
    }



    @Override
    public String getLevel() {return level;}
    @Override
    public int getAge() {return age;}

    @Override
    public String getInitials() {
        // Assuming initials are the first letter of each word in the name
        String[] nameParts = getName().split(" ");
        StringBuilder initials = new StringBuilder();
        for (String part : nameParts) {
            initials.append(part.charAt(0));
        }
        return initials.toString();
    }
}