/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milton
 */
public class ProjectOverview
{
    private Connection connection;
    private ArrayList<Project> projects;
    
    public ProjectOverview()
    {
        projects = new ArrayList<>();
        
        try
        {
            connection = new Connection();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProjectOverview.class.getName()).log(Level.SEVERE, null, ex);
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
