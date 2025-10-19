import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeUserDatabase {
    private ArrayList<EmployeeUser> records;
    private String filename;

    public EmployeeUserDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<EmployeeUser>();
    }

    public void readFromFile() throws Exception
    {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found!");
            return;
        }
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            EmployeeUser employee = createRecordFrom(line);
            if (employee != null) {
                records.add(employee);
            }
        }
        input.close();
    }
    public ArrayList<EmployeeUser> returnAllRecords()
    {
        return records;
    }
    public EmployeeUser createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length == 5) {

            EmployeeUser employee = new EmployeeUser(parts[0], parts[1], parts[2], parts[3], parts[4]);
            return employee;
        }
        return null;
    }

    public boolean contains(String key)
    {
        
        for(int i=0;i<records.size();i++)
        {
            records.get(i);
            if(records.get(i).getSearchKey().equals(key))
            {
                return true;
            }
        }
        return false;
    }

    public String getRecord(String key)
    {
        EmployeeUser employee;
        for(int i=0;i<records.size();i++)
        {
            
            if(records.get(i).getSearchKey().equals(key))
            {
                String line=records.get(i).lineRepresentation();
                return line;
            }
        }
        return null;

    }
    public void insertRecord(EmployeeUser record)
    {
       this.records.add(record);
        try
        {
            saveToFile();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    public void deleteRecord(String key)
    {
        int flag = 0 ;
        for(int i=0;i<records.size();i++)
        {
            if(records.get(i).getSearchKey().equals(key))
            {
                records.remove(i);
                flag = 1 ;
            }
        }
        if (flag == 0)
        {
            System.out.println("Record not found.");
        }
        else
        {
            try {
                saveToFile();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void saveToFile() throws Exception
    {
        FileWriter file = new FileWriter(filename,false);
        for(int i=0;i<records.size();i++)
        {
            file.write(records.get(i).lineRepresentation()+"\n");
        }
        file.close();
    }
    public String getFilename() {
        return this.filename;
    }
}
