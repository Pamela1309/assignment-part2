public enum CompetitionType
{
    VolleyballIndoors(" VolleyballIndoors"), VolleyballOutdoors(" VolleyballOutdoors");
    private String type;
    
    private CompetitionType(String ty){
        type = ty;
    }
    
    public String toString(){
        return type;
    }
}