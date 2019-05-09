package Main;
import java.util.Scanner;

import Admin.Admin_Details;
import Customer.Customer_Details;

public class Main_Movie {
    public static void main (String[] args)
    
    {
    	Scanner sc1 = new Scanner (System.in);
    	
    	System.out.println("What kind of a user you are ?");
    	
    	System.out.println("press 1 For Admin user");
    	
    	System.out.println("Press 2 for customer user");
    	
        int choice = sc1.nextInt();
        
        if (choice ==1)
        {
        	
        	Admin_Details a = new Admin_Details ();
        	
        	a.displayMenu();
        	
        }
    	
       else if (choice == 2)
    	   
       {
    	   Customer_Details c1 = new Customer_Details ();
    	   
    	   c1.displayMenu();
    	   
       }
    	
       else 
       {
    	   System.out.println("your choice is wrong");
    	   
    	   System.exit(0);
    	   
    	   
       }
    	
    }

}
