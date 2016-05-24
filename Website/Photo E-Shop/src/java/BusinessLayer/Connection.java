/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import Package.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        String createProjectQuery = "INSERT INTO \"Project\"(CompanyID, \"Name\", Client, StartDate, EndDate) VALUES (" + companyID + ", " + projectName + ", " + clientName + ", " + startDate + ", " + endDate + ");";
        
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
    
    public int getCompanyID(String username) throws SQLException
    {
        int companyID = -1;
        
        if(username.isEmpty())
        {
            String query = "SELECT CompanyID FROM Company WHERE UserID = (SELECT UserID FROM \"User\" WHERE Email = '" + username + "')";   
            
            try
            {
                if(database.Connect())
                {
                    ResultSet resultSet = database.GetQuery(query);
                    
                    if(resultSet != null)
                    {
                        if(resultSet.next())
                        {
                            companyID = resultSet.getInt("CompanyID");
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
        return companyID;
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
                        try
                        {
                            database.Close();   
                        }
                        catch(Exception exception)
                        {
                            
                        }
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
    
    public ArrayList<Project> getProjects(int companyID) throws ClassNotFoundException
    {
        ArrayList<Project> projects = new ArrayList<>();
        
        String query = "SELECT ProjectID, CompanyID, \"Name\", Client, StartDate, EndDate FROM \"Project\" WHERE CompanyID = " + companyID + ")";

        if(database.Connect())
        {
            try
            {
                //success = true;
                ResultSet resultSet = database.GetQuery(query);

                if(resultSet != null)
                {
                    if(resultSet.next())
                    {
                        projects.add(new Project(resultSet.getInt("ProjectID"), resultSet.getInt("CompanyID"), resultSet.getString("\"Name\""), resultSet.getString("Client"), resultSet.getDate("StartDate"), resultSet.getDate("EndDate")));
                    }                    
                }                        
            }
            catch(Exception ex)
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
        
        return projects;
    }
    
    public void deleteProject(int projectID)
    {
        String query = "DELETE FROM \"Project\" WHERE ProjectID = " + projectID;
        
        try
        {
            if(database.Connect())
            {
                database.DeleteQuery(query);
            }
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean updateProject(int projectID, String name, String client, Date startDate, Date endDate)
    {
        boolean success = false;
        String query = "UPDATE \"Project\" SET \"Name\"='" + name + "', Client='" + client + "', StartDate=" + startDate + ", EndDate=" + endDate + " WHERE ProjectID=" + projectID;
        
        try
        {
            if(database.Connect())
            {
                success = database.updateQuery(query);
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
        return success;
    }
}
