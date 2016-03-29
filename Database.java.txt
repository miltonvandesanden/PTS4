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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class Database 
{

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        ResultSet test = null;
        
	public Database() throws SQLException 
	{
            //code in classes
	    /*Connect();
            test = GetQuery("select * from \"User\"");
            while (myRs.next()) 
            {
                System.out.println(myRs.getString("UserID") + ", " + myRs.getString("Email"));
            }            
            Close();*/
            
            //inserting 
            
            /*Connect();
            DoQuery("Insert into \"User\"(UserID, Email, IsAdmin) values(3, 'stefanoverhoeve44@gmail.com', 'N')");
            while (myRs.next()) 
            {
                System.out.println(test.getString("UserID") + ", " + myRs.getString("Email"));
            }
            Close();*/
	}
        public boolean Connect()
        {
            try
            {
                myConn = DriverManager.getConnection("jdbc:oracle:thin:@//fhictora01.fhict.local:1521/fhictora", "dbi310866", "O4g03ym3r8");
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
            }
            
            return true;
        }
        // query =  select * from \"User\"
        public ResultSet GetQuery(String query)
        {
            try
            {
                myStmt = myConn.createStatement();
                return myRs = myStmt.executeQuery(query);
                //code om door result set te lopen in bovenliggende klasse
                /*while (myRs.next()) 
                {
                        System.out.println(myRs.getString("UserID") + ", " + myRs.getString("Email"));
                }*/
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
            }
            return null;
        }
        //Delete from \"User\" where Email = 'stefanoverhoeve44@gmail.com'"
        //Insert into \"User\"(UserID, Email, IsAdmin) values("3, 'stefanoverhoeve44@gmail.com', 'N')
        public int DoQuery(String query) throws SQLException
        {
            int rowsAffected = myStmt.executeUpdate(query);
            return rowsAffected;
        }
        
        public void Close()
        {
            try
            {
                if (myRs != null) 
                {
                    myRs.close();
                }
                if (myStmt != null) 
                {
                    myStmt.close();
                }
                if (myConn != null) 
                {
                    myConn.close();
                }
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
            }
        }
            
}


