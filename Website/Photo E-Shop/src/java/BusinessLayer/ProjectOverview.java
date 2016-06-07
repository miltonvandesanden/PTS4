/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Milton
 */
public class ProjectOverview
{
    private Connection connection;
    private ArrayList<Project> projects;
    
    public ProjectOverview() throws SQLException
    {
        projects = new ArrayList<>();
        
        try
        {
            connection = new Connection();
        }
        catch(Exception exception)
        {
            
        }
    }
    
    public void loadProjectsFromDb(String username)
    {
        projects = new ArrayList<>();
        try
        {
            int companyId = connection.getCompanyID(username);

            if(companyId != -1)
            {
                projects = connection.getProjectsFromUser(companyId);
            }            
        }
        catch(Exception exception)
        {
            
        }
    }
    
    public boolean CreateProject(int companyID, String projectName, String clientName, Date startDate, Date endDate) throws SQLException
    {
        boolean succes = false;
        
        try
        {
            if(!projectName.isEmpty() && !clientName.isEmpty() && startDate != new Date() && endDate != new Date() && startDate.before(endDate))
            {
                succes = connection.CreateProject(companyID, projectName, clientName, startDate, endDate);
            }
        }
        catch(Exception exception)
        {
            
        }
        return succes;
    }
    
    public boolean DeleteProject(int projectID)
    {
        boolean success = false;
        
        try
        {
            success = connection.deleteProject(projectID);
        }
        catch(Exception exception)
        {
            
        }
        
        return success;
    }
}
