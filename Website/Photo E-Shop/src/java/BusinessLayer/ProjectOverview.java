/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import java.io.File;
import java.sql.SQLException;
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
    
    public ProjectOverview()
    {
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
        
        if(!projectName.isEmpty() && !clientName.isEmpty() && startDate != new Date() && endDate != new Date() && startDate.before(endDate))
        {
            Connection connection = new Connection();
            succes = connection.CreateProject(companyID, projectName, clientName, startDate, endDate);
        }
        return succes;
    }
    
    public void AddPicsToProject(int projectID, File[] pics)
    {
        
    }
}
