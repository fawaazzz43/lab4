/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.customerproduct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author pc
 */
public class CustomerProduct {

    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate){
        
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = false;
        
    }

    public String getCustomerSSN() {
        
        return customerSSN;
        
    }

    public String getProductID() {
        
        return productID;
        
    }

    public LocalDate getPurchaseDate() {
        
        return purchaseDate;
        
    }
    
    public boolean isPaid(){
        
        return paid;
        
    }
    
    public void setPaid(boolean paid){
        
       this.paid = paid;
        
    }
    
    public String lineRepresentation(){
        
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       String formattedDate = purchaseDate.format(formatter);

        
       String line = customerSSN + "," + productID + "," + formattedDate + "," + paid;
       
       return line;
    
    }
    
    public String getSearchKey(){
    
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       String formattedDate = purchaseDate.format(formatter); 
        
       String searchKey = customerSSN + "," + productID + "," + formattedDate;
       
       return searchKey;
    
    }
    
}
