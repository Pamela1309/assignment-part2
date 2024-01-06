public enum CompetitionType
{
    VolleyballMen(" VolleyballMen"), VolleyballWomen(" VolleyballWomen");
    private String type;
    
    private CompetitionType(String ty){
        type = ty;
    }
    
    public String toString(){
        return type;
    }
}