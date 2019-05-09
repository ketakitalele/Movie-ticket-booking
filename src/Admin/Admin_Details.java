package Admin;

import java.sql.*;
import java.util.Scanner;

public class Admin_Details {
	Scanner sc1 = new Scanner(System.in);
	static Connection con = null;

	static

	{
		try {
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Driver loaded");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie?user=root&password=ketaki");

			System.out.println("Connection established");

		}

		catch (ClassNotFoundException  | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


	public void displayMenu()

	{

		System.out.println("Please tell us what you want to do");

		System.out.println("1. Add Movies");

		System.out.println("2. Update Movies");

		System.out.println("3. Delete Movies");

		System.out.println("4. View Movies");

		int choice = sc1.nextInt();

		if (choice == 1)

		{
			addMovies();
		}

		else if (choice == 2)

		{

			updateMovies();

		}

		else if (choice == 3)

		{
			deleteMovies();

		}

		else if (choice == 4) {
			viewMovies();

		}
        
		else 
			
		{
			System.out.println("Your chocie is wrong");
			System.exit(0);
			
		}
	}

	public void addMovies()

	{

		System.out.println("Please Enter Movie name");

		String movie= sc1.next();

		System.out.println("Please Enter Movie hall");

		String theater = sc1.next();

		System.out.println("Please Enter City");

		String city = sc1.next();
		
		System.out.println("total tickets");
		
		int tickets = sc1.nextInt();
		
		System.out.println("time slot");
		
		String time = sc1.next();


		PreparedStatement pstmt = null;

		String query = "insert into movie_master values (?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, 0);
			pstmt.setString(2, theater);
			pstmt.setString(3, movie);	
			pstmt.setString(4, city);
			pstmt.setInt(5, tickets);
			pstmt.setString(6, time);
			
			int count = pstmt.executeUpdate();

			System.out.println(count + " movie added successfully");

		} catch (SQLException e)

		{

			e.printStackTrace();

		}

	}

	public void updateMovies()

	{

		System.out.println("Enter the Movie ID you want to update");

		int id = sc1.nextInt();

		System.out.println("Please Enter New Movie name");

		String new_movie_name = sc1.next();

		System.out.println("Please Enter New Movie Halll");

		String new_movie_hall = sc1.next();

		System.out.println("Please Enter new Movie city");

		String new_city = sc1.next();
		
		System.out.println("Please enter number of total tickets");
		
		int new_total_tickets = sc1.nextInt();
		
		System.out.println("Please enter time slot");
		
		String new_time = sc1.next();

		String query = "update movie_master set movie= ?, theater = ?, city = ? , tickets = ? , time = ? where id = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, new_movie_name);

			pstmt.setString(2, new_movie_hall);

			pstmt.setString(3, new_city);

			//pstmt.setInt(4, id);
			
			pstmt.setInt(4, new_total_tickets);
			
			pstmt.setString(5, new_time);
			
			pstmt.setInt(6,id);

			int count = pstmt.executeUpdate();

			System.out.println(count + " movie info updated");

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteMovies()

	{
		System.out.println("Enter the movie ID you want to Delete");

		int id = sc1.nextInt();

		String query = "delete from movie_master where id = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, id);

			int count = pstmt.executeUpdate();

			System.out.println(count + " movies deleted");

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			System.out.println("id\t movie\t theater\t city\t tickets\t time");

			System.out.println("--------------------------------------------------------");

			while (rs.next())

			{
				int id = rs.getInt(1);

				String movie_name = rs.getString(2);

				String movie_hall = rs.getString(3);

				String city = rs.getString(4);
				
				int tickets = rs.getInt(5);
				
				String time = rs.getString(6);

				System.out.println(id +"\t "+ movie_name +"\t      " +   movie_hall +"\t         " +city +"\t     "+tickets +"\t     "+time);

			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
