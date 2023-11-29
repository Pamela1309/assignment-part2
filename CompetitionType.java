public enum CompetitionType {
    MenStreet(" Mens Street"), MenPark(" Mens Park"), WomanStreet(" Womens Street"), WomanPark(" Womans Park");
    private String type;

    private CompetitionType(String ty){
        type = ty;
    }

    public String toString(){
        return type;
    }
}
