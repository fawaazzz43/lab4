
public class ProductDatabase extends Database<Product> {
    
    public ProductDatabase (String filename)
    {
        super(filename);
    }

    
    @Override
    public Product createRecordFrom(String line)
    {
        String [] linesplit = line.split(",");
        if(linesplit.length==6)
            return new Product(linesplit[0],linesplit[1],linesplit[2],linesplit[3],Integer.parseInt(linesplit[4]),Float.parseFloat(linesplit[5]));
        else 
        return null;
    }
}