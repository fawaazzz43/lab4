public class EmployeeUser {
    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) 
    {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }

    public String lineRepresentation()
    {
       String EmployeeData = String.join(",", employeeId, name, email, address, phoneNumber);
       return EmployeeData;
    }

    public String getSearchKey() {
        return employeeId;
    }
}
