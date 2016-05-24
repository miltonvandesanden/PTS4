/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milton
 */
public class Project
{
    private Connection connection;
    
    private int projectID;
    private int companyID;
    private String name;
    private String client;
    private Date startDate;
    private Date endDate;
    
    private ArrayList<Picture> pictures;
    private ArrayList<String> emails;

    public Project(int projectID, int companyID, String name, String client, Date startDate, Date endDate)
    {
        this.projectID = projectID;
        this.companyID = companyID;
        this.name = name;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;        
    }
    
    public Project(int companyID, String name, String client, Date startDate, Date endDate)
    {
        this.companyID = companyID;
        this.name = name;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        
        try
        {
            connection = new Connection();
            connection.CreateProject(companyID, name, client, startDate, endDate);
        }
        catch (Exception ex)
        {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getProjectID()
    {
        return projectID;
    }
    
    public int getCompanyID()
    {
        return companyID;
    }
    
    public String getName()
    {
        return name;
    }    
}
