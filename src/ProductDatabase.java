import java.io.*;
import java.util.ArrayList;


public class ProductDatabase  {
    
    private ArrayList<Product> records;
    private String filename;

    public ProductDatabase (String filename)
    {
        this.filename=filename;
        records=new ArrayList();
    }

    public void readFromFile() throws IOException
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

    public Product createRecordFrom(String line)
    {
        String [] linesplit = line.split(",");
        if(linesplit.length==6)
            return new Product(linesplit[0],linesplit[1],linesplit[2],linesplit[3],Integer.parseInt(linesplit[4]),Float.parseFloat(linesplit[5]));
        else 
        return null;
    }

    public ArrayList<Product> returnAllRecords()
    {
        return records;
    }

    public boolean contains(String key)
    {
        for(int i=0;i<records.size();i++)
            if(key.equals(records.get(i).getSearchKey()))
                return true;
        return false;
    }

    public Product getRecord(String key) 
    {   int i=0;
        for(i=0;i<records.size();i++)
            if(key.equals(records.get(i).getSearchKey()))
                return records.get(i);
        return null;
    }

    public void insertRecord(Product record)
    {
        if(contains(record.getSearchKey())==false)
        {
            this.records.add(record);
        }
        else
        {
            System.out.println("Product ID must be unique");
        }
    }

    public void deleteRecord(String key)
    {
        int i = 0 ;
        for( i = 0 ; i < records.size() ; i++ )
        {
            if(key.equals(records.get(i).getSearchKey()))
                {records.remove(records.get(i));
                    return;}
        }
        if(i==records.size())
            System.out.println("Record not found.");

    }

    public void saveToFile()
    {
        try
        {
            FileWriter fr = new FileWriter(filename);
            for(int i=0;i<records.size();i++)
                fr.write(records.get(i).lineRepresentation()+"\n");

            System.out.println("Data saved to file successfully.");
            fr.close();
        }
        catch(IOException e)
        {
            System.out.println("Error while saving file: " + e.getMessage());
        }
    }

}