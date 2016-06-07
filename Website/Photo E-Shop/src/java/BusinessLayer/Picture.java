/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import java.sql.Blob;
import java.util.ArrayList;

/**
 *
 * @author Milton
 */
public class Picture
{
    private int pictureID;
    private int projectID;
    private int height;
    private int width;
    //0 = color, 1 = b&w, -1 = undefined
    private int colorType;
    private Blob pic;
    private ArrayList<String> emails;
    
    public Picture(int pictureID, int projectID, int height, int width, int colorType, Blob pic)
    {
        this.pictureID = pictureID;
        this.projectID = projectID;
        this.height = height;
        this.width = width;
        
        switch(colorType)
        {
            case 0:
                this.colorType = colorType;
                break;
            case 1:
                this.colorType = colorType;
                break;
            default:
                this.colorType = -1;
                break;
        }
        
        this.pic = pic;
        emails = new ArrayList<>();
    }
    
    public Picture(int pictureID, int projectID, int height, int width, int colorType, Blob pic, ArrayList<String> emails)
    {
        this.pictureID = pictureID;
        this.projectID = projectID;
        this.height = height;
        this.width = width;
        
        switch(colorType)
        {
            case 0:
                this.colorType = colorType;
                break;
            case 1:
                this.colorType = colorType;
                break;
            default:
                this.colorType = -1;
                break;
        }
        
        this.pic = pic;
        this.emails = emails;
    }
    
    public int getPictureID()
    {
        return pictureID;
    }
    
    public void setPictureID(int pictureID)
    {
        this.pictureID = pictureID;
    }
    
    public int getProjectID()
    {
        return projectID;
    }
    
    public void setProjectID(int projectID)
    {
        this.projectID = projectID;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public void setHeight(int height)
    {
        this.height = height;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public void setWidth(int width)
    {
        this.width = width;
    }
    
    public int getColorType()
    {
        return colorType;
    }
    
    public void setColorType(int colorType)
    {
        switch(colorType)
        {
            case 0:
                this.colorType = colorType;
                break;
            case 1:
                this.colorType = colorType;
                break;
            default:
                this.colorType = -1;
                break;
        }
    }
    
    public Blob getPic()
    {
        return pic;
    }
    
    public void setPic(Blob pics)
    {
        this.pic = pic;
    }
    
    public ArrayList<String> getEmails()
    {
        return emails;
    }
    
    public void setEmails(ArrayList<String> emails)
    {
        this.emails = emails;
    }
    
    public void addEmail(String email)
    {
        if(!emails.contains(email))
        {
            emails.add(email);
        }
    }
    
    public void removeEmail(String email)
    {
        if(emails.contains(email))
        {
            emails.remove(email);
        }
    }
}
