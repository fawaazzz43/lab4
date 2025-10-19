

import java.io.IOException;
import static java.lang.System.exit;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class employee_role
{
    private ProductDatabase PD ;
    private CustomerProductDatabase CPD ;

    public employee_role(ProductDatabase PD,CustomerProductDatabase CPD)
    {
        this.PD = PD ;
        try {
            this.PD.readFromFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.CPD = CPD ;
        this.CPD.readFromFile();
    }
    public void add_product (String product_id, String product_name, String manufacturer_name, String supplier_name, int quantity, Double price )
    {
        String line = product_id + "," + product_name + "," + manufacturer_name + "," + supplier_name + "," + quantity + "," + price ;

        Product p ;
        p = PD.createRecordFrom (line) ;
        PD.insertRecord (p) ;
    }

    public Product[] get_list_of_products()
    {
        return PD.returnAllRecords().toArray(new Product[0]);
    }

    public  CustomerProduct[] get_list_of_purchasing_operations()
    {
        return CPD.returnAllRecords().toArray(new CustomerProduct[0]);
    }

    public boolean purchaseProduct(String customer_ssn, String product_id, LocalDate purchase_date)
    {
        String line = customer_ssn + "," + product_id + "," + purchase_date ;
        CustomerProduct u ;
        u = CPD.createRecordFrom(line) ;

        Product p =  null ;


        if ( p.getQuantity(product_id) == 0 )
        {
            return false;
        }
        else
        {
            CPD.insertRecord(u) ;
            p.setQuantity( p.getQuantity(product_id)-1 );
            PD.saveToFile();
            return true;
        }
    }

    public double return_product(String customer_ssn, String product_id, LocalDate purchase_date ,LocalDate return_date)
    {
          if ( return_date.isBefore(purchase_date) )
          {
            return -1 ;
          }
          else if ( !PD.contains(product_id) )
          {
              return -1 ;
          }
          else if ( PD.getRecord(product_id) == null )
          {
              return -1 ;
          }
          else
          {
                if ( Math.abs(ChronoUnit.DAYS.between(purchase_date,return_date) ) > 14 )
              {
                  return -1 ;
              }
              else
              {
                  Product p = null ;
                  p.setQuantity( p.getQuantity(product_id)+1 );

                  PD.deleteRecord(product_id) ;

                  PD.saveToFile();

                  return p.getPrice() ;
              }
          }
    }

    public boolean apply_payment(String customer_ssn , LocalDate purchase_date)
    {
        int flag = 0 ;
        for ( CustomerProduct i : CPD.returnAllRecords() )
        {
            if (purchase_date.isEqual(i.getPurchaseDate()))
            {
                if ( !i.isPaid() )
                {
                    i.setPaid(true) ;
                    flag = 1 ;
                }
                CPD.saveToFile();

            }
        }
        if ( flag == 1 )
        {
            return true ;
        }
        else
        {
            return  false ;
        }
    }

    public void logout ()
    {
        CPD.saveToFile();
        PD.saveToFile();

       System.out.println("employee logged out and data saved.");
       exit(0);
    }
}
