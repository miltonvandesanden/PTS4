/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Milton
 */
public class ProjectOverview
{
    private static volatile ProjectOverview instance;
    private Connection connection;
    
    private ArrayList<Project> projects;
    
    public ProjectOverview()
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
    
    public static ProjectOverview getInstance()
    {
        if(instance == null)
        {
            synchronized (ProjectOverview.class)
            {
                if(instance == null)
                {
                    instance = new ProjectOverview();
                }
            }
        }
        return instance;
    }
    
    public void loadProjects(String username)
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
        
    public ArrayList<Project> getProjects()
    {
        return projects;
    }
    
    public Project getProject(int projectID)
    {
        Project result = null;
        
        for(Project project : projects)
        {
            if(project.getProjectID() == projectID)
            {
                result = project;
            }
        }
        
        return result;
    }
    
    public boolean deletePicture(int projectID, int pictureID)
    {
        boolean success = false;
        
        for(Project project : projects)
        {
            if(project.getProjectID() == projectID)
            {
                success = project.deletePicture(pictureID);
            }
        }
        
        return success;
    }
    
    public boolean createPicture(int projectID, int height, int width, int colorType, Blob pic)
    {
        boolean success = false;
        
        for(Project project : projects)
        {
            if(project.getProjectID() == projectID)
            {
                success = project.createPicture(projectID, height, width, colorType, pic);
            }
        }
        return success;
    }
    
    /*public boolean updateProject(int projectID, int companyID, String name, String client,Date startDate, Date endDate)
    {
        
    }*/
}
