/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import Package.Database;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Milton
 */
public class Connection
{
    private Database database;
    public Connection() throws SQLException
    {
        database = new Database();
    }
    
    public boolean CheckLogIn(String username, String password) throws SQLException, Exception
    {
        boolean success = false;
        
        if(username != null && password != null)
        {
            //String LogInQuery = "SELECT * FROM Company WHERE username = '" + username + "' AND password = '" + password + "'";
            String LogInQuery = "SELECT * FROM \"User\" WHERE Email = '" + username + "' AND \"Password\" = '" + password + "'";
            try
            {
                //success = true;
                if(database.Connect())
                {
                    try
                    {
                        //success = true;
                        ResultSet resultSet = database.GetQuery(LogInQuery);
                        if(resultSet != null)
                        {
                            //success = true;
                            if(resultSet.next())
                            {
                                if(resultSet.getInt("isCompany") == 1)
                                {
                                    int UserID = resultSet.getInt("UserID");
                                
                                    String checkAcceptedQuery = "SELECT * FROM Company WHERE UserID = " + UserID + " AND isAccepted = 1";
                                
                                    ResultSet resultSet2 = database.GetQuery(checkAcceptedQuery);
                                
                                    if(resultSet2 != null)
                                    {
                                        if(resultSet2.next())
                                        {
                                            success = true;
                                        }
                                    }
                                }
                                else
                                {
                                    success = true;
                                }
                            }                    
                        }                        
                    }
                    catch(Exception ex)
                    {
                        
                    }
                    finally
                    {
                        database.Close();
                    }
                }
            }
            catch(Exception ex)
            {
                throw ex;
            }
        }
        
        return success;
    }
        
    /*public boolean isPhotographer(String username)
    {        
        boolean isPhotographer = false;
        
        if(username != null)
        {
            //String LogInQuery = "SELECT * FROM Company WHERE username = '" + username + "' AND password = '" + password + "'";
            String LogInQuery = "SELECT * FROM Company WHERE username = '" + username + "' AND isAccepted = 1";
            try
            {
                //success = true;
                if(database.Connect())
                {
                    try
                    {
                        success = true;
                        ResultSet resultSet = database.GetQuery(LogInQuery);
                        if(resultSet != null)
                        {
                            //success = true;
                            if(resultSet.next())
                            {
                                success = true;
                            }                    
                        }                        
                    }
                    catch(Exception ex)
                    {
                        
                    }
                    finally
                    {
                        //database.Close();
                    }
                }
            }
            catch(Exception ex)
            {
                throw ex;
            }
        }
        
        return false;
    }*/
}
