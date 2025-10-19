import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        try
        {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            records.clear();
            while (line != null) {
                records.add(createRecordFrom(line));
                line = br.readLine();
            }
            br.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }

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
            if(records.get(i).getSearchKey().equals(key))
            {
                return true;
            }
        }
        return false;
    }

    public EmployeeUser getRecord(String key)
    {
        EmployeeUser employee;
        for(int i=0;i<records.size();i++)
        {
            
            if(records.get(i).getSearchKey().equals(key))
            {
                return records.get(i);
            }
        }
        return null;

    }
    public void insertRecord(EmployeeUser record)
    {
       if(contains(record.getSearchKey())==false)
        {
            this.records.add(record);
        }
        else
        {
            System.out.println("employee ID must be unique");
        }
    }
    public void deleteRecord(String key)
    {
        int flag = 0 ;
        for(int i=0;i<records.size();i++)
        {
            if(records.get(i).getSearchKey().equals(key))
            {
                records.remove(records.get(i));
                flag = 1 ;
                return;
            }
        }
        if (flag == 0)
        {
            System.out.println("Record not found.");
        }
    }
    public void saveToFile() throws Exception
    {
        FileWriter file = new FileWriter(filename);
        for(int i=0;i<records.size();i++)
        {
            file.write(records.get(i).lineRepresentation()+"\n");
        }
        file.close();
    }
}
