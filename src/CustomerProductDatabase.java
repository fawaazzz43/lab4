import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerProductDatabase {

    private ArrayList<CustomerProduct> records;
    private String filename;

    public CustomerProductDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    public void readFromFile() {
        try {
            File file = new File(filename);

            if (!file.exists())
            {
                records = new ArrayList<>();
                return;
            }

            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();

                if (line.isEmpty())
                    continue;

                CustomerProduct record = createRecordFrom(line);

                if (record != null)
                    records.add(record);
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // CustomerProductDatabase.java

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

    public ArrayList<CustomerProduct> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key))
                return true;
        }
        return false;
    }

    public CustomerProduct getRecord(String key) {
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key))
                return record;
        }
        return null;
    }

    public void insertRecord(CustomerProduct record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
        }
        
    }

    public void deleteRecord(String key) {
        CustomerProduct toRemove = null;

        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key)) {
                toRemove = record;
                break;
            }
        }

        if (toRemove != null)
        {
            records.remove(toRemove);
        }
        
    }

    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter(filename);

            for (CustomerProduct record : records) {
                writer.write(record.lineRepresentation() + "\n");
            }
            writer.close();

            System.out.println("Data saved to file successfully.");

        } catch (IOException e) {
            System.out.println("Error while saving file: " + e.getMessage());
        }
    }
}
