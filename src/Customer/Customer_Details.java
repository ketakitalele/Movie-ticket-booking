package Customer;
import java.sql.*;
import java.util.Scanner;

public class Customer_Details {
	Scanner sc1 = new Scanner(System.in);
	static Connection con = null;

	static

	{

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			System.out.println("Driver loaded");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie?user=root&password=ketaki");
			
			System.out.println("Connection established");

		}

		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	

	public void displayMenu()

	{	
		//login();
		
		//System.out.println("Hi Customer ! what you want to do?");
		//System.out.println("enter username");
		//String uname = sc1.next();
		System.out.println("Hello what you want to do?");
		System.out.println("1. View Movies");

		System.out.println("2. Book Tickits");
		
		int choice = sc1.nextInt();

		if (choice == 1)

		{

			viewMovies();

		}


		else if (choice == 2)

		{
			bookTickit();

		}

		else

		{
			System.out.println("Your chocie is wrong");
			System.exit(0);

		}

	}

	public void viewMovies()

	{

		Statement stmt = null;

		ResultSet rs = null;

		String query = "select * from movie_master";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			System.out.println("id\t movie\t theater\t city\t time");

			System.out.println("--------------------------------------------------------");

			while (rs.next()) {

				int id = rs.getInt(1);

				String movie_name = rs.getString(2);

				String movie_hall = rs.getString(3);

				String city = rs.getString(4);
				
				String time_slot = rs.getString(6);

				System.out.println(id + "\t " + movie_name + "\t " + movie_hall + "\t       " + city+"\t   "+time_slot);

			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	 public void bookTickit()

	 	{
		 	System.out.println("enter user name ");
		 	String uname = sc1.next();
		 	
	        System.out.println("enter the movie");
	        String movie = sc1.next();
	        System.out.println("enter the theater");
	        String theater = sc1.next();
	        System.out.println("enter the number of tickets you want to book");
	        int tickets = sc1.nextInt();
	        System.out.println("enter the time slot u want");
	        String time = sc1.next();
	       
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	       
	        String query = "select tickets from movie_master where movie =? and theater =? and time =?";
	       
 	        try
	        {
	            pstmt = con.prepareStatement(query);
	            //pstmt.setInt(1, tickets);
	            pstmt.setString(1, movie);
	            pstmt.setString(2, theater);
	            pstmt.setString(3, time);
	      
	            //System.out.println("hello");
	            rs = pstmt.executeQuery();
	            if (rs.next())
	            { 
	            	//System.out.println("hello");
	                int dbtickets = rs.getInt("tickets");
	                //System.out.println(dbtickets);
	                if(tickets <= dbtickets)
	                {	
	            
	                    String query1 = "update movie_master set tickets=? where movie =? and theater =? and time =?";
	                    int new_tickets = dbtickets-tickets;
	                    
	                    //System.out.println(new_tickets);
	                   
	                    pstmt = con.prepareStatement(query1);
	             
	                    pstmt.setInt(1, new_tickets);
	               
	                    pstmt.setString(2,movie);
	                   
	                    pstmt.setString(3, theater);
	                   
	                    pstmt.setString(4, time);
	                    
	                    pstmt.executeUpdate();
	                    
	                    System.out.println("Movie: "+movie);
	                    System.out.println("Theater "+theater);
	                    
	                   
	                    System.out.println("Number of tickets have been booked: "+tickets);
	                    System.out.println("Time slot: "+time);
	                   
	                    int perprice = 250;
	                    int totalcost = perprice*tickets;
	                   
	                    System.out.println("Your total amount is "+totalcost);
	                    System.out.println("Booking done successfully");
	                    
	                    String query2 = "insert into user_details values (?,?,?,?,?,?)";
	                    pstmt = con.prepareStatement(query2);
	                    pstmt.setInt(1, 0);
	                    pstmt.setString(2,uname);
	                    pstmt.setString(3,movie);
	                    pstmt.setString(4,theater);
	                    pstmt.setInt(5,tickets);
	                    pstmt.setString(6,time);
	                    int count = pstmt.executeUpdate();
	                    
	                }
	                else
	                {
	                    System.out.println("Tickets not available");
	                   
	               
	                }
	            }
	            else
	            {
	                System.out.println("Please enter valid details "); 
	            }
	            
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	           
	        }	
	 	}
	/* public void user_details()
	 {
		 System.out.println("enter username");
		 String uname = sc1.next();
		 
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 String query = "insert into user_details values (?,?,?,?,?,?)";
		 
		 try
		 {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,0);
			pstmt.setString
		 }
				 
		 
	 }*/
	 
	}


