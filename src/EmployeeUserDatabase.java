
public class EmployeeUserDatabase extends Database<EmployeeUser> {
    public EmployeeUserDatabase(String filename) {
        super(filename);
    }
    
    @Override
    public EmployeeUser createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length == 5) {

            EmployeeUser employee = new EmployeeUser(parts[0], parts[1], parts[2], parts[3], parts[4]);
            return employee;
        }
        return null;
    }
}
