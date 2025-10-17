import java.util.Scanner;

class mainAdmin {
    public static void main(String[] args) throws Exception
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter File Name: ");
        String filename= input.nextLine();
        

        Adminrole admin = new Adminrole(filename);

       if(admin.getListOfEmployees().length == 0){
           
           return;
}

        while (true) {
        System.out.println("Choose an operation:");
        System.out.println("1. Add Employee");
        System.out.println("2. Remove Employee");
        System.out.println("3. List Employees");
        System.out.println("4. Logout");
        int choice = input.nextInt();
        input.nextLine(); 

            switch (choice) {
            case 1:
            System.out.println("\n\n");
                System.out.print("Enter Employee ID: ");
                String id = input.nextLine();
                System.out.print("Enter Name: ");
                String name = input.nextLine();
                System.out.print("Enter Address: ");
                String address = input.nextLine();
                System.out.print("Enter Email: ");
                String email = input.nextLine();
                System.out.print("Enter Phone Number: ");
                String phoneNumber = input.nextLine();
                if(phoneNumber.length() != 11){
                    System.out.println("Invalid phone number. It should be 11 digits.");
                    break;
                }
                admin.addEmployee(id, name, address, email, phoneNumber);
                System.out.println("press logout to save data");
                break;
            case 2:
                System.out.print("Enter Employee ID to remove: ");
                String removeId = input.nextLine();
                admin.removeEmployee(removeId);
                System.out.println("press logout to save data");
                break;
            case 3:
                EmployeeUser[] employees = admin.getListOfEmployees();
                System.out.println("List of Employees:");
                for(int i = 0; i < employees.length; i++) {
                    System.out.println((i+1) + ". " + employees[i].lineRepresentation());
                }
                
                break;
            case 4:
                admin.logout();
                break;
            default:
                System.out.println("Invalid choice.");
        }
            
        }
    }
    }
