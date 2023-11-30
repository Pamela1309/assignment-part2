public class Staff {
    public int StaffID;
    public Name name;

    public Staff(int staffID, Name name){
        this.StaffID = StaffID;
        this.name = name;
    }

    public void setStaffID(int Value){
        this.StaffID = Value;
    }

    public int getStaffID(){
        return StaffID;
    }

    public void setName (Name name){
        this.name = name;
    }

    public String getName(){
        return this.name.toString();
    }

    public String toString(){
        return StaffID + name.toString();
    }
}
