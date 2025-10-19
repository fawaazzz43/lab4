import static java.lang.System.exit;
public class Adminrole {
    private EmployeeUserDatabase database;

  public Adminrole()
  {
      try
      {
          //this.database = new EmployeeUserDatabase("Employee.txt");
          this.database = new EmployeeUserDatabase("Employee.txt");
          this.database.readFromFile();
      }
      catch (Exception e)
      {
          System.out.println("error reading file");
      }
}


    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber)
    {
       if(database.contains(employeeId))
       {
           System.out.println("Employee with this ID already exists.");
           return;
       }
    
        EmployeeUser newEmployee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
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
    public void logout()
    {
        try
        {
            database.saveToFile();
            System.out.println("Admin logged out and data saved.");
        }
        catch (Exception e)
        {
            System.out.println("error saving file");
        }
        exit(0);

    }

}
