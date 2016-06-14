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
    
    /*public Project(int projectID, int companyID, String name, String client, Date startDate, Date endDate)
    {   
        this.projectID = projectID;
        this.companyID = companyID;
        this.name = name;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        
        pictures = new ArrayList<>();
        emails = new ArrayList<>();
    }*/

    
    public int getProjectID()
    {
        return projectID;
    }
    
    /*public void setProjectID(int projectID)
    {
        this.projectID = projectID;
    }*/
    
    public int getCompanyID()
    {
        return companyID;
    }
    
    /*public void setCompanyID(int companyID)
    {
        this.companyID = companyID;
    }*/
    
    public String getName()
    {
        return name;
    }
    
    public boolean setName(String name)
    {
        boolean success = false;
        
        try
        {
            success = connection.setNameOfProject(projectID, name);
        }
        catch(Exception exception)
        {
            
        }
        
        return success;
    }
    
    public String getClient()
    {
        return client;
    }
    
    public boolean setClient(String client)
    {
        boolean success = false;
        
        try
        {
            success = connection.setClientOfProject(projectID, client);
        }
        catch(Exception exception)
        {
            
        }
        
        return success;
    }
    
    public Date getStartDate()
    {
        return startDate;
    }
    
    public boolean setStartDate(Date startDate)
    {
        boolean success = false;
        
        if(startDate.before(endDate))
        {
            try
            {
                success = connection.setStartDateOfProject(projectID, startDate);
            }
            catch(Exception exception)
            {
            
            }
        }
        
        return success;
    }
    
    public Date getEndDate()
    {
        return endDate;
    }
    
    public boolean setEndDate(Date endDate)
    {
        boolean success = false;
        
        if(endDate.after(startDate))
        {
            try
            {
                success = connection.setEndDateOfProject(projectID, endDate);
            }
            catch(Exception exception)
            {
                
            }
        }
        
        return success;
    }
    
    public ArrayList<Picture> getPictures()
    {
        return pictures;
    }
    
    public void setPictures(ArrayList<Picture> pictures)
    {
        this.pictures = pictures;
    }
    
    public boolean setPicturesInDatabase(ArrayList<Picture> pictures)
    {
        boolean success = false;
        
        try
        {
            success = connection.setPicturesOfProject(this, pictures);
        }
        catch(Exception exception)
        {

        }
        
        return success;
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
    
    public boolean createPicture(int projectID, int height, int width, int colorType, Blob pic)
    {
        boolean succes = false;
        
        try
        {
            String colorTypeString;
            
            switch (colorType)
            {
                case 0:
                    colorTypeString = "color";
                    break;
                case 1:
                    colorTypeString = "blackWhite";
                    break;
                default:
                    colorTypeString = "undefined";
                    break;
            }
            
            succes = connection.InsertPicture(projectID, height, width, colorTypeString, pic);
        }
        catch(Exception exception)
        {
            
        }
        return succes;
    }
    
    public boolean addEmail(String email)
    {
        boolean success = false;
        
        try
        {
            success = connection.addEmailToProject(projectID, email);
        }
        catch(Exception exception)
        {
            
        }
        
        return success;
    }
}
