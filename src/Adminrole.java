public class Adminrole {
    private EmployeeUserDatabase database;

  public Adminrole(String filename) throws Exception {
    this.database = new EmployeeUserDatabase(filename);
    this.database.readFromFile();
}


    public void addEmployee(String employeeId, String name, String position, String department, String email)
    {
       if(database.contains(employeeId))
       {
           System.out.println("Employee with this ID already exists.");
           return;
       }
    
        EmployeeUser newEmployee = new EmployeeUser(employeeId, name, position, department, email);
        this.database.insertRecord(newEmployee);
        System.out.println("New employee added.");
        
    }

    public void removeEmployee(String employeeId)
    {
        if (!database.contains(employeeId)) {
            System.out.println("Employee with this ID does not exist.");
            return;
        }
        database.deleteRecord(employeeId);
        System.out.println("Employee with ID " + employeeId + " has been removed.");
        
    }
    public EmployeeUser[] getListOfEmployees()
    {
        EmployeeUser[] employees = new EmployeeUser[database.returnAllRecords().size()];
        for (int i = 0; i < database.returnAllRecords().size(); i++) {
            employees[i] = database.returnAllRecords().get(i);
        }
        return employees;

    }
    public void logout() throws Exception
    {
        database.saveToFile();
        System.out.println("Admin logged out and data saved.");
    }

}
