/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Stefan
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
    
    public Project(Connection connection, int projectID, int companyID, String name, String client, Date startDate, Date endDate)
    {
        this.connection = connection;
        
        this.projectID = projectID;
        this.companyID = companyID;
        this.name = name;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        
        pictures = new ArrayList<>();
        emails = new ArrayList<>();
    }
    
        public Project(int projectID, int companyID, String name, String client, Date startDate, Date endDate)
    {   
        this.projectID = projectID;
        this.companyID = companyID;
        this.name = name;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        
        pictures = new ArrayList<>();
        emails = new ArrayList<>();
    }

    
    public int getProjectID()
    {
        return projectID;
    }
    
    public void setProjectID(int projectID)
    {
        this.projectID = projectID;
    }
    
    public int getCompanyID()
    {
        return companyID;
    }
    
    public void setCompanyID(int companyID)
    {
        this.companyID = companyID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getClient()
    {
        return client;
    }
    
    public void setClient(String client)
    {
        this.client = client;
    }
    
    public Date getStartDate()
    {
        return startDate;
    }
    
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }
    
    public Date getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
    
    public ArrayList<Picture> getPictures()
    {
        return pictures;
    }
    
    public void setPictures(ArrayList<Picture> pictures)
    {
        this.pictures = pictures;
    }

    public boolean deletePicture(int pictureID)
    {
        boolean success = false;
        
        try
        {
            success = connection.deletePicture(pictureID);
        }
        catch(Exception exception)
        {
            
        }
        
        return success;
    }
    
    public boolean createPicture(int pictureID, int projectID, int height, int width, int colorType, Blob pic)
    {
        boolean succes = false;
        
        try
        {
            String colorTypeString = "";
            
            if(colorType == 0)
            {
                colorTypeString = "color";
            }
            else if(colorType == 1)
            {
                colorTypeString = "blackWhite";
            }
            else
            {
                colorTypeString = "undefined";
            }
            
            succes = connection.InsertPicture(projectID, height, width, colorTypeString, pic);
        }
        catch(Exception exception)
        {
            
        }
        return succes;

    }
}
