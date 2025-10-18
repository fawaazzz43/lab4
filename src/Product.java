public class Product{
    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity ;
    private float price;

    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity)
    {
        if(quantity>=0)
            this.quantity = quantity;
        else
            System.out.println("Error: quantity must be non-negative.");
    }

    public String lineRepresentation()
    {
        return productID +","+ productName +","+ manufacturerName +","+ supplierName +","+ String.valueOf(quantity) +","+ String.valueOf(price);
    }

    public String getSearchKey()
    {
        return productID;
    }

}