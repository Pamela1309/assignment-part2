import java.util.ArrayList;

public class StaffList {
    private ArrayList<Staff> staffList;

    public StaffList() {
        this.staffList = new ArrayList<>();
    }

    public void addStaff(Staff staff) {
        staffList.add(staff);
    }

    public Staff findStaffById(int staffId) {
        for (Staff staff : staffList) {
            if (staff.getStaffId() == staffId) {
                return staff;
            }
        }
        return null; // Return null if no match is found
    }
}