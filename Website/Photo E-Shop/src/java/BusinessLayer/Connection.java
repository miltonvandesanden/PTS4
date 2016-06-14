/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import Package.Database;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Milton
 */
public class Connection
{
    private Database database;
    public Connection() throws SQLException
    {
        database = new Database();
    }
    
    public boolean CreateProject(int companyID, String projectName, String clientName, Date startDate, Date endDate)
    {
        boolean success = false;
        
        String createProjectQuery = "INSERT INTO \"Project\"(PROJECTID, COMPANYID, \"Name\", CLIENT, STARTDATE, ENDDATE) VALUES (PROJECTSEQUENCE.nextval, " + companyID + ", " + projectName + ", " + clientName + ", to_date(" + startDate.getDate() + "-" + startDate.getMonth() + "-" + startDate.getYear() + ", 'DD-Mon-YY'), to_date(" + endDate.getDate() + "-" + endDate.getMonth() + "-" + endDate.getYear() + ", 'DD-Mon-YY'));";
        
        try
        {
            if(database.Connect())
            {
                database.InsertQuery(createProjectQuery);
            }
        }
        catch(ClassNotFoundException | SQLException exception)
        {
            
        }
        finally
        {
            
        }
        return success;
    }
    
    public boolean CheckLogIn(String username, String password) throws SQLException, Exception
    {
        boolean success = false;
        
        if(username != null && password != null)
        {
            //String LogInQuery = "SELECT * FROM Company WHERE username = '" + username + "' AND password = '" + password + "'";
            String LogInQuery = "SELECT * FROM \"User\" WHERE EMAIL = '" + username + "' AND \"Password\" = '" + password + "'";
            try
            {
                //success = true;
                if(database.Connect())
                {
                    try
                    {
                        //success = true;
                        ResultSet resultSet = database.GetQuery(LogInQuery);
                        if(resultSet != null)
                        {
                            //success = true;
                            if(resultSet.next())
                            {
                                if(resultSet.getInt("isCompany") == 1)
                                {
                                    int UserID = resultSet.getInt("UserID");
                                
                                    String checkAcceptedQuery = "SELECT * FROM COMPANY WHERE USERID = " + UserID + " AND ISACCEPTED = 1";
                                
                                    ResultSet resultSet2 = database.GetQuery(checkAcceptedQuery);
                                
                                    if(resultSet2 != null)
                                    {
                                        if(resultSet2.next())
                                        {
                                            success = true;
                                        }
                                    }
                                }
                                else
                                {
                                    success = true;
                                }
                            }                    
                        }                        
                    }
                    catch(Exception ex)
                    {
                        
                    }
                    finally
                    {
                        database.Close();
                    }
                }
            }
            catch(Exception ex)
            {
                throw ex;
            }
        }
        
        return success;
    }
    
    public boolean deleteProject(int projectID)
    {
        boolean result = false;
        String query = "DELETE FROM \"Project\" WHERE PROJECTID = " + projectID;
        
        try
        {
            if(database.Connect())
            {
                database.deleteQuery(query);
                result = true;
            }
        }
        catch(Exception exception)
        {
            
        }
        finally
        {
            try
            {
                database.Close();
            }
            catch(Exception exception)
            {
                
            }
        }
        
        return result;
    }
    
    public boolean deletePicture(int pictureID)
    {
        boolean result = false;
        String query = "DELETE FROM PICTURE_USER WHERE PICTUREID = " + pictureID;
        String query2 = "DELETE FROM PICTURE WHERE PICTUREID = " + pictureID;
        try
        {
            if(database.Connect())
            {
                database.deleteQuery(query);
                database.deleteQuery(query2);
                result = true;
            }
        }
        catch(Exception exception)
        {
            
        }
        finally
        {
            try
            {
                database.Close();
            }
            catch(Exception exception)
            {
                
            }
        }
        
        return result;
    }
    
    /*public int getCompanyID(String username) throws ClassNotFoundException
    {
        int companyID = -1;
    }
    */
    
    public int getCompanyID(String username)
    {
        int companyId = -1;
        
        if(!username.isEmpty())
        {
            String query = "SELECT COMPANYID FROM COMPANY WHERE USERID = (SELECT USERID FROM \"User\" WHERE EMAIL = '" + username + "')";

            try
            {
                if(database.Connect())
                {
                    database.GetQuery(query);

                    ResultSet resultSet = database.GetQuery(query);

                    if(resultSet != null)
                    {
                        if(resultSet.next())
                        {
                            companyId = resultSet.getInt("companyID");
                        }                    
                    }         
                }
            }
            catch(ClassNotFoundException | SQLException exception)
            {

            }
            finally
            {
                try
                {
                    database.Close();
                }
                catch(Exception exception)
                {

                }
            }
        }    
        
        return companyId;
    }

    public ArrayList<Project> getProjectsFromUser(int companyID)
    {
        ArrayList<Project> projects = new ArrayList<>();
        
        String query = "SELECT PROJECTID, \"Name\", CLIENT, STARTDATE, ENDDATE FROM \"Project\" WHERE COMPANYID = " + companyID;
        
        try
        {
            if(database.Connect())
            {
                ResultSet resultSet = database.GetQuery(query);
                
                if(resultSet != null)
                {
                    while(resultSet.next())
                    {
                        int projectID = resultSet.getInt("ProjectID");
                        
                        Project project = new Project(this, projectID, companyID, resultSet.getString("name"), resultSet.getString("client"), resultSet.getDate("startDate"), resultSet.getDate("endDate"));
                        
                        project.setPictures(getPicturesFromProject(projectID));
                        
                        projects.add(project);
                        
                        
                    }
                }       
            }
        }
        catch(ClassNotFoundException | SQLException exception)
        {
            System.out.println("exception");
        }
        finally
        {
            try
            {
                database.Close();
            }
            catch(Exception exception)
            {
                
            }
        }
        
        return projects;
    }
    
    public ArrayList<Picture> getPicturesFromProject(int projectID)
    {
        ArrayList<Picture> pictures = new ArrayList<>();
        
        try
        {
            if(database.Connect())
            {
                String query = "SELECT PICTUREID, HEIGHT, WIDTH, COLORTYPE, PICTURE FROM PICTURE WHERE PROJECTID = " + projectID;

                ResultSet resultSet = database.GetQuery(query);

                if(resultSet != null)
                {
                    while(resultSet.next())
                    {
                        int pictureID = resultSet.getInt("PictureID");
                        Picture picture = new Picture(pictureID, projectID, resultSet.getInt("Height"), resultSet.getInt("Width"), resultSet.getInt("colorType"), resultSet.getBlob("picture"));

                        picture.setEmails(getEmailsFromPicture(pictureID));

                        pictures.add(picture);
                    }
                }
            }
        }
        catch(SQLException | ClassNotFoundException exception)
        {
            
        }
        finally
        {
            try
            {
                database.Close();
            }
            catch(Exception exception)
            {
                
            }
        }
        
        return pictures;
    }
    
    public ArrayList<String> getEmailsFromPicture(int pictureID)
    {
        ArrayList<String> emails = new ArrayList<>();
        
        try
        {
            if(database.Connect())
            {
                String query = "SELECT EMAIL FROM User WHERE USERID = (SELECT USERID FROM PICTURE_USER WHERE PICTUREID = " + pictureID + ")";

                ResultSet resultSet = database.GetQuery(query);

                if(resultSet != null)
                {
                    while(resultSet.next())
                    {
                        emails.add(resultSet.getString("Email"));
                    }
                }
            }
        }
        catch(SQLException | ClassNotFoundException exception)
        {
            
        }
        finally
        {
            try
            {
                database.Close();
            }
            catch(Exception exception)
            {
                
            }
        }
        
        return emails;
    }
    
    public boolean InsertPicture(int projectID, int height, int width, String colorType, Blob picture)
    {
        boolean success = false;
        String createImageQuery = "INSERT INTO \"PICTURE\"(\"PICTUREID\", \"PROJECTID\", \"HEIGHT\", \"WIDTH\", \"COLORTYPE\", \"PICTURE\") VALUES (PictureSequence.nextval, " + projectID + ", " + height + ", " + width + ", " + colorType + ", "+ picture + ")";
        
        try
        {
            if(database.Connect())
            {
                database.InsertQuery(createImageQuery);
                success = true;
            }
        }
        catch(ClassNotFoundException | SQLException exception)
        {
            
        }
        finally
        {
            try
            {
                database.Close();
            }
            catch(Exception exception)
            {
                
            }
        }
        return success;
    }
    
//    public ArrayList<Picture> getPictures(int projectID)
//    {
//        ArrayList<Picture> pictures = new ArrayList<>();
//        
//        String query = "SELECT ";
//        
//        return pictures;
//    }
    
    public boolean isPhotographer(String username) throws Exception
    {        
        boolean isPhotographer = false;
        
        if(username != null)
        {
            //String LogInQuery = "SELECT * FROM Company WHERE username = '" + username + "' AND password = '" + password + "'";
            String LogInQuery = "SELECT * FROM COMPANY WHERE USERID = '(SELECT USERID FROM User WHERE EMAIL = '" + username + "') AND ISACCEPTED = 1";
            try
            {
                //success = true;
                if(database.Connect())
                {
                    try
                    {
                        ResultSet resultSet = database.GetQuery(LogInQuery);
                        
                        if(resultSet != null)
                        {
                            if(resultSet.next())
                            {
                                isPhotographer = true;
                            }                    
                        }                        
                    }
                    catch(Exception ex)
                    {
                        
                    }
                    finally
                    {
                        try
                        {
                            database.Close();
                        }
                        catch(Exception exception)
                        {
                            
                        }
                    }
                }
            }
            catch(Exception ex)
            {
                throw ex;
            }
        }
        
        return isPhotographer;
    }
    
    /*public boolean updateProject(int projectID, int companyID, String name, String client,Date startDate, Date endDate)
    {
        boolean success = false;
        String query = "UPDATE Project SET ";
        
        
        return success;
    }*/
    
    public boolean setNameOfProject(int projectID, String name)
    {
        boolean success = false;
        String query = "UPDATE \"Project\" SET \"Name\"=" + name + "WHERE PROJECTID=" + projectID;
        
        try
        {
            if(database.Connect())
            {
                success = database.InsertQuery(query);
            }
        }
        catch(ClassNotFoundException | SQLException exception)
        {
            System.out.println("exception");
        }
        finally
        {
            try
            {
                database.Close();
            }
            catch(Exception exception)
            {
                
            }
        }
        
        return success;
    }
    
    public boolean setClientOfProject(int projectID, String client)
    {
        boolean success = false;
        String query = "UPDATE \"Project\" SET CLIENT=" + client + "WHERE PROJECTID=" + projectID;
        
        try
        {
            if(database.Connect())
            {
                success = database.InsertQuery(query);
            }
        }
        catch(ClassNotFoundException | SQLException exception)
        {
            System.out.println("exception");
        }
        finally
        {
            try
            {
                database.Close();
            }
            catch(Exception exception)
            {
                
            }
        }
        
        return success;        
    }
    
    public boolean setStartDateOfProject(int projectID, Date startDate)
    {
        boolean success = false;
        String query = "UPDATE \"Project\" SET STARTDATE=" + startDate + "WHERE PROJECTID=" + projectID;
        
        try
        {
            if(database.Connect())
            {
                success = database.InsertQuery(query);
            }
        }
        catch(ClassNotFoundException | SQLException exception)
        {
            System.out.println("exception");
        }
        finally
        {
            try
            {
                database.Close();
            }
            catch(Exception exception)
            {
                
            }
        }
        
        return success;        
    }
    
    public boolean setEndDateOfProject(int projectID, Date endDate)
    {
        boolean success = false;
        String query = "UPDATE \"Project\" SET ENDDATE=" + endDate + "WHERE PROJECTID=" + projectID;
        
        try
        {
            if(database.Connect())
            {
                success = database.InsertQuery(query);
            }
        }
        catch(ClassNotFoundException | SQLException exception)
        {
            System.out.println("exception");
        }
        finally
        {
            try
            {
                database.Close();
            }
            catch(Exception exception)
            {
                
            }
        }
        
        return success;        
    }
    
    public boolean setPicturesOfProject(Project project, ArrayList<Picture> pictures)
    {
        boolean success = true;
        
        for(Picture picture : project.getPictures())
        {
            String query = "DELETE FROM PICTURE_USER WHERE PICTUREID=" + picture.getPictureID();
            String query2 = "DELETE FROM PICTURE WHERE PICTUREID=" + picture.getPictureID();
            
            try
            {
                if(database.Connect())
                {
                    if(!database.deleteQuery(query))
                    {
                        success = false;
                    }
                    
                    if(!database.deleteQuery(query2))
                    {
                        success = false;
                    }
                }
            }
            catch(Exception exception)
            {
                
            }
            finally
            {
                try
                {
                    database.Close();
                }
                catch(Exception exception)
                {
                    
                }
            }
        }
        
        for(Picture picture : pictures)
        {
            String color = "undefined";
            
            if(picture.getColorType() == 0)
            {
                color = "color";
            }
            else if(picture.getColorType() == 1)
            {
                color = "blackWhite";
            }
            
            if(!InsertPicture(picture.getProjectID(), picture.getHeight(), picture.getWidth(), color, picture.getPic()));
            {
                success = false;
            }
        }
        
        return success;        
    }
    
    public boolean addEmailToProject(int projectID, String email)
    {
        boolean success = false;
        
        return success;
    }
}
