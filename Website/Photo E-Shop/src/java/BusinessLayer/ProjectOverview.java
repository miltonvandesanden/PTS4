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
    
    public ArrayList<Project> getProjects()
    {
        return projects;
    }
    
    public Project getProject(int projectID)
    {
        Project project = null;
        
        for(Project project2 : projects)
        {
            if(project2.getProjectID() == projectID)
            {
                project = project2;
            }
        }
        
        return project;
    }
    
    public boolean createProject(String username, String projectName, String clientName, Date startDate, Date endDate) throws SQLException
    {   
        boolean result = false;
        
        if(!projectName.isEmpty() && !clientName.isEmpty() && startDate != new Date() && endDate != new Date() && startDate.before(endDate))
        {
            try {
                Project project = new Project(connection.getCompanyID(username), projectName, clientName, startDate, endDate);
            } catch (Exception exception) {
                Logger.getLogger(ProjectOverview.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
        return result;
    }
    
    public void deleteProject(int projectID)
    {
        connection.deleteProject(projectID);
    }
    
    public void refreshProjects(int companyID)
    {
        try {
            this.projects = connection.getProjects(companyID);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectOverview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
