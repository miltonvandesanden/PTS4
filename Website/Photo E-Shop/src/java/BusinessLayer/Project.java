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
    private int projectID;
    private int companyID;
    private String name;
    private String client;
    private Date startDate;
    private Date endDate;
    
    private ArrayList<Picture> pictures;
    
    public Project(int projectID, int companyID, String name, String client, Date startDate, Date endDate)
    {
        this.projectID = projectID;
        this.companyID = companyID;
        this.name = name;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        
        pictures = new ArrayList<>();
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
    
    public void addPicture(Picture picture)
    {
        pictures.add(picture);
    }
    
    public void removePicture(Picture picture)
    {
        pictures.remove(picture);
    }       
}
