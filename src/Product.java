public class Product extends Record{
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

    public int getQuantity(String product_id){
        return this.quantity;
    }

    public void setQuantity(int quantity)
    {
        if(quantity>=0)
            this.quantity = quantity;
        else
            System.out.println("Error: quantity must be non-negative.");
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String lineRepresentation()
    {
        return productID +","+ productName +","+ manufacturerName +","+ supplierName +","+ String.valueOf(quantity) +","+ String.valueOf(price);
    }

    @Override
    public String getSearchKey()
    {
        return productID;
    }

}