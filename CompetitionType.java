public enum CompetitionType
{
    Surfing(" Surfing"), Skating(" Skating");
    private String type;
    
    private CompetitionType(String ty){
        type = ty;
    }
    
    public String toString(){
        return type;
    }
}