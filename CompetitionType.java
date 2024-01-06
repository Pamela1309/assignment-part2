public enum CompetitionType
{
    VolleyballMen(" VolleyballIndoors"), VolleyballWomen(" VolleyballOutdoors");
    private String type;
    
    private CompetitionType(String ty){
        type = ty;
    }
    
    public String toString(){
        return type;
    }
}