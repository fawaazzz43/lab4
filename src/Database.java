import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public abstract class Database<T extends Record> {
    private ArrayList<T> records;
    private String filename;

    public Database(String filename) {
        this.filename = filename;
        this.records = new ArrayList<T>();
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

    public ArrayList<T> returnAllRecords()
    {
        return records;
    }
    
    abstract protected T createRecordFrom(String line); 

    public boolean contains(String key)
    {
        
        for(int i=0;i<records.size();i++)
        {
            if((records.get(i)).getSearchKey().equals(key))
            {
                return true;
            }
        }
        return false;
    }

    
    public T getRecord(String key)
    {
        for(int i=0;i<records.size();i++)
        {
            
            if((records.get(i)).getSearchKey().equals(key))
            {
                return  records.get(i);
            }
        }
        return null;

    }
    
    public void insertRecord(T record)
    {
       if(contains(record.getSearchKey())==false)
        {
            this.records.add(record);
        }
        else
        {
            System.out.println("Record with the same key already exists.");
        }
    }
    
    public void deleteRecord(String key)
    {
        int flag = 0 ;
        for(int i=0;i<records.size();i++)
        {
            if(( records.get(i)).getSearchKey().equals(key))
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
            file.write(( records.get(i)).lineRepresentation()+"\n");
        }
        file.close();
    }
}
