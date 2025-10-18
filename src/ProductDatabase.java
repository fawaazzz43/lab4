import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        records.clear();
        while (line != null)
        {
        
        records.add(createRecordFrom(line));
        line = br.readLine();

        }
        br.close();
        System.out.println("File is readed successfully");
    }

    public Product createRecordFrom(String line)
    {
        String [] linesplit = line.split(",");
        return new Product(linesplit[0],linesplit[1],linesplit[2],linesplit[3],Integer.parseInt(linesplit[4]),Float.parseFloat(linesplit[5]));
    }

    public ArrayList<Product> returnAllRecords()
    {
        return records;
    }

    public boolean contains(String key )
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
            records.add(record);
        else
            System.out.println("Product ID must be unique");
    }

    public void deleteRecord(String key)
    {
        int i=0;
        for(i=0;i<records.size();i++)
            if(key.equals(records.get(i).getSearchKey()))
                records.remove(records.get(i));
    }

    public void saveToFile() throws IOException
    {
        FileWriter fr = new FileWriter(filename);
        for(int i=0;i<records.size();i++)
            fr.write(records.get(i).lineRepresentation()+"\n");

        fr.close();
    }

}