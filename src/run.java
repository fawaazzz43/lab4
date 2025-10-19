import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

class run {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println("who are you?");
        System.out.println("1.admin");
        System.out.println("2.employee");
        Integer me = scan.nextInt();
        scan.nextLine();

        switch (me) {

            case 1: {
                System.out.println("welcome admin");
                Adminrole A = new Adminrole();
                while (true) {
                    
                    System.out.println("what you want?");
                    System.out.println("1.add employee");
                    System.out.println("2.remove employee");
                    System.out.println("3.logout");

                    Integer i = scan.nextInt();
                    scan.nextLine();

                    switch (i) {
                        case 1: {
                            System.out.println("Enter employee ID");
                            String id = scan.nextLine();
                            System.out.println("Enter employee name");
                            String name = scan.nextLine();
                            System.out.println("Enter employee email");
                            String email = scan.nextLine();
                            System.out.println("Enter employee address");
                            String address = scan.nextLine();
                            System.out.println("Enter employee phone number");
                            String phoneNumber = scan.nextLine();

                            A.addEmployee(id, name, email, address, phoneNumber);
                            break;
                        }
                        case 2: {
                            System.out.println("Enter employee ID");
                            String id = scan.nextLine();

                            A.removeEmployee(id);
                            break;
                        }
                        case 3: {
                            A.logout();
                            break;
                        }
                        default: {
                            System.out.println("Invalid input");
                            break;
                        }
                    }
                    
                }
                
            }

            case 2: {
                System.out.println("welcome employee");
                    ProductDatabase P = new ProductDatabase("D:\\programming\\java\\lab4\\Products.txt");
                    CustomerProductDatabase C = new CustomerProductDatabase("D:\\programming\\java\\lab4\\CustomersProducts.txt");
                    employee_role E = new employee_role(P,C);

                while (true) {
                    
                    System.out.println("what you want?");
                    System.out.println("1.add product");
                    System.out.println("2.show products info");
                    System.out.println("3.show purchasing operation info");
                    System.out.println("4.purchase product");
                    System.out.println("5.return product");
                    System.out.println("6.apply payment");
                    System.out.println("7.logout");

                    Integer J = scan.nextInt();
                    scan.nextLine();

                    switch (J) {
                        case 1: {
                            System.out.println("Enter product ID");
                            String id = scan.nextLine();
                            System.out.println("Enter product name");
                            String name = scan.nextLine();
                            System.out.println("Enter manufacturer name");
                            String manufacturer = scan.nextLine();
                            System.out.println("Enter supplier name");
                            String supplier = scan.nextLine();
                            System.out.println("Enter product quantity");
                            Integer quantity = scan.nextInt();
                            scan.nextLine();

                            E.add_product(id, name, manufacturer, supplier, quantity);
                            break;
                        }
                        case 2: {
                            for (Product p : E.get_list_of_products()) {
                                System.out.println(p.lineRepresentation());
                                System.out.println();
                            }
                            break;
                        }
                        case 3: {
                            for (CustomerProduct cp : E.get_list_of_purchasing_operations()) {
                                System.out.println(cp.lineRepresentation());
                                System.out.println();
                            }
                            break;
                        }
                        case 4: {
                            System.out.println("Enter customer SSN");
                            String ssn = scan.nextLine();
                            System.out.println("Enter product ID");
                            String id = scan.nextLine();
                            LocalDate d_t = LocalDate.now();

                            E.purchaseProduct(ssn, id, d_t);
                            break;
                        }
                        case 5: {
                            System.out.println("Enter customer SSN");
                            String ssn = scan.nextLine();
                            System.out.println("Enter product ID");
                            String id = scan.nextLine();
                            System.out.println("Enter purchase date");
                            System.out.println("Enter the day");
                            int day = scan.nextInt();
                            scan.nextLine();
                            System.out.println("Enter the month");
                            int month = scan.nextInt();
                            scan.nextLine();
                            System.out.println("Enter the year");
                            int year = scan.nextInt();
                            scan.nextLine();

                            LocalDate d_p = LocalDate.of(year, month, day);
                            LocalDate d_t = LocalDate.now();

                            E.return_product(ssn, id, d_p, d_t);
                            break;
                        }
                        case 6: {
                            System.out.println("Enter customer SSN");
                            String ssn = scan.nextLine();
                            System.out.println("Enter purchase date");
                            System.out.println("Enter the day");
                            int day = scan.nextInt();
                            scan.nextLine();
                            System.out.println("Enter the month");
                            int month = scan.nextInt();
                            scan.nextLine();
                            System.out.println("Enter the year");
                            int year = scan.nextInt();
                            scan.nextLine();

                            LocalDate d_p = LocalDate.of(year, month, day);

                            E.apply_payment(ssn, d_p);
                            break;
                        }
                        case 7: {
                            E.logout();
                            break;
                        }
                        default: {
                            System.out.println("Invalid input");
                            break;
                        }
                    }
                    
                }
            }

            default: {
                System.out.println("Invalid input");
                break;
            }
        }
    }
}