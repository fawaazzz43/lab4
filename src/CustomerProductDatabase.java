import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProductDatabase extends Database<CustomerProduct> {

    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        try {
            String[] parts = line.split(",");

            if (parts.length != 4)
                return null;

            String ssn = parts[0].trim();
            String productID = parts[1].trim();
            String dateStr = parts[2].trim();
            String paidStr = parts[3].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(dateStr, formatter);

            boolean paid = Boolean.parseBoolean(paidStr);


            CustomerProduct record = new CustomerProduct(ssn, productID, date);
            record.setPaid(paid);

            return record;

        } catch (Exception e) {
            System.out.println("Error in line: " + line); //
            return null;
        }
    }   
}