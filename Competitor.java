public abstract class Competitor {
    private int competitorNumber;
    private String name;
    private String country;

    public Competitor(int competitorNumber, String name, String country) {
        this.competitorNumber = competitorNumber;
        this.name = name;
        this.country = country;
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

    public abstract double getOverallScore();

    public String getFullDetails() {
        return String.format("Competitor number %d, name %s, country %s.\n%s is a %s aged %d and has an overall score of %.2f.",
                competitorNumber, name, country, name, getLevel(), getAge(), getOverallScore());
    }

    public String getShortDetails() {
        return String.format("CN %d (%s) has overall score %.2f.", competitorNumber, getInitials(), getOverallScore());
    }

    protected abstract String getLevel();

    protected abstract int getAge();

    protected abstract String getInitials();
}