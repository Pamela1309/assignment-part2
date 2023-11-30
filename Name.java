public class Name {
    protected String firstName;
    protected String surname;
    protected String middleName;

    public Name(String firstName, String surname){
        this.firstName = firstName;
        this.surname = surname;
    }

    public String toString(){
        return firstName + surname;
    }
}
