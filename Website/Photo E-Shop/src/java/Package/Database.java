/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;
/**
 *
 * @author Stefan
 */
import java.sql.*;
public class Database {

	public Database() throws SQLException 
	{

                Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try 
		{
			System.out.println("Setting up connection with the database...");
			myConn = DriverManager.getConnection("jdbc:oracle:thin:@//fhictora01.fhict.local:1521/fhictora", "dbi310866", "O4g03ym3r8");
			
			System.out.println("Selecting from the database...\n");
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("select * from \"User\"");
			
			while (myRs.next()) 
			{
				System.out.println(myRs.getString("UserID") + ", " + myRs.getString("Email"));
			}
		}
		catch (Exception exc) 
		{
			exc.printStackTrace();
		}
		finally 
		{
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
	}

}


