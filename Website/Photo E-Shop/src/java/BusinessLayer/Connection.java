/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import Package.Database;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
    
    public boolean CreateProject(int companyID, String projectName, String clientName, Date startDate, Date endDate)
    {
        boolean success = false;
        
        String createProjectQuery = "INSERT INTO \"Project\"(CompanyID, \"Name\", Client, StartDate, EndDate) VALUES (" + companyID + ", " + projectName + ", " + clientName + ", to_date(" + startDate.getDate() + "-" + startDate.getMonth() + "-" + startDate.getYear() + ", 'DD-Mon-YY'), to_date(" + endDate.getDate() + "-" + endDate.getMonth() + "-" + endDate.getYear() + ", 'DD-Mon-YY'));";
        
        try
        {
            if(database.Connect())
            {
                database.InsertQuery(createProjectQuery);
            }
        }
        catch(ClassNotFoundException | SQLException exception)
        {
            
        }
        finally
        {
            
        }
        return success;
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
    
    public boolean deleteProject(int projectID)
    {
        boolean result = false;
        String query = "DELETE FROM \"Project\" WHERE ProjectID = " + projectID;
        
        try
        {
            if(database.Connect())
            {
                database.deleteQuery(query);
                result = true;
            }
        }
        catch(Exception exception)
        {
            
        }
        finally
        {
            try
            {
                database.Close();
            }
            catch(Exception exception)
            {
                
            }
        }
        
        return result;
    }
    
    /*public int getCompanyID(String username) throws ClassNotFoundException
    {
        int companyID = -1;
    }
    */
    
    public int getCompanyID(String username)
    {
        int companyId = -1;
        
        if(!username.isEmpty())
        {
            String query = "SELECT companyID FROM Company WHERE UserID = (SELECT UserID FROM \"User\" WHERE Email = '" + username + "')";

            try
            {
                if(database.Connect())
                {
                    database.GetQuery(query);

                    ResultSet resultSet = database.GetQuery(query);

                    if(resultSet != null)
                    {
                        if(resultSet.next())
                        {
                            companyId = resultSet.getInt("companyID");
                        }                    
                    }         
                }
            }
            catch(Exception exception)
            {

            }
            finally
            {
                try
                {
                    database.Close();
                }
                catch(Exception exception)
                {

                }
            }
        }    
        
        return companyId;
    }
    
    public boolean InsertPicture(int ProjectID, int Height, int Width, String ColorType, Blob Picture)
    {
        boolean success = false;
        String createImageQuery = "INSERT INTO \"Picture\"(\"PictureID\", \"ProjectID\", \"Height\", \"Width\", \"colorType\", \"picture\") VALUES (PictureSequence.nextval" + ProjectID + ", " + Height + ", " + Width + ", " + ColorType + ", "+ Picture + ")";
        
        try
        {
            if(database.Connect())
            {
                database.InsertQuery(createImageQuery);
                success = true;
            }
        }
        catch(ClassNotFoundException | SQLException exception)
        {
            
        }
        finally
        {
            
        }
        return success;
    }
    
//    public ArrayList<Picture> getPictures(int projectID)
//    {
//        ArrayList<Picture> pictures = new ArrayList<>();
//        
//        String query = "SELECT ";
//        
//        return pictures;
//    }
    
    public boolean isPhotographer(String username) throws Exception
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
                        ResultSet resultSet = database.GetQuery(LogInQuery);
                        
                        if(resultSet != null)
                        {
                            if(resultSet.next())
                            {
                                isPhotographer = true;
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
        
        return isPhotographer;
    }
}
