/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import BusinessLayer.Connection;
import BusinessLayer.Project;
import BusinessLayer.ProjectOverview;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Stefan
 */
@WebServlet(urlPatterns = {"/Projectoverviewservlet"})
@MultipartConfig
public class Projectoverviewservlet extends HttpServlet{
    
    private JFrame frame = new JFrame();
    ProjectOverview po = ProjectOverview.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        frame.setAlwaysOnTop(true);
        if(request.getParameter("submit")!=null)
        {
            Database database = null;
            try 
            {
                database = new Database();
                database.Connect();
            } 
            catch (SQLException | ClassNotFoundException ex) 
            {
                Logger.getLogger(Projectoverviewservlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try
            {
                String contentType = request.getContentType();
                if ((contentType.indexOf("multipart/form-data") >= 0)) 
                {
                    try
                    { 
                        Part filePart = request.getPart("fileupload");
                        Image image = ImageIO.read(filePart.getInputStream());
                        String INSERT_PICTURE = "INSERT INTO \"PICTURE\"(\"PICTUREID\", \"PROJECTID\", \"HEIGHT\", \"WIDTH\", \"COLORTYPE\", \"PICTURE\") VALUES (PictureSequence.nextval, 1,"+ image.getHeight(null) +","+ image.getWidth(null) +", 'Color', ?)";
                        InputStream is = null;
                        PreparedStatement ps = null;
                        try 
                        {
                            is = filePart.getInputStream();
                            ps = database.myConn.prepareStatement(INSERT_PICTURE);
                            ps.setBlob(1, is);
                            ps.executeUpdate();
                            database.myConn.commit();
                            JOptionPane.showMessageDialog(frame, "De afbeelding is succesvol geupload.");
                        } 
                        finally 
                        {
                            try
                            {
                                ps.close();
                                is.close();
                            }
                            catch(Exception exception)
                            {

                            }
                        }
                    }
                    catch(IOException | ServletException | SQLException ex) 
                    {
                        System.out.println(ex);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Er is iets fout gegaan probeer het opnieuw");
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
            response.sendRedirect("projectoverview.jsp");
        }
        
        if(request.getParameter("deleteproject")!=null)
        {
                String[] selectresults = request.getParameterValues("selectproject");
                po.deleteProject(Integer.parseInt(selectresults[0]));
                
        }
        
        if(request.getParameter("openproject")!=null)
        {
            try
            {
                String[] selectresults = request.getParameterValues("selectproject");
                Project project = po.getProject(Integer.parseInt(selectresults[0]));
                request.setAttribute("project", project);
                request.getRequestDispatcher("projectoverview.jsp").forward(request, response);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
            
        }
        
        if(request.getParameter("Save")!=null)
        {
            
            Project project = (Project)request.getAttribute("project");
            if(project != null)
            {
                try
                {
                    System.out.println(request.getParameter("startdate"));
                    po.updateProject(project.getProjectID(), request.getParameter("name"), request.getParameter("client"), new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startdate")), new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("enddate")));
                }
                catch(Exception ex)
                {
                    
                }
            }
            else
            {
                String username = "";
                for(Cookie cookie : request.getCookies())
                {
                    if(cookie.getName().equals("Email"))
                    {
                        username = cookie.getValue();
                    }
                }
                if(!username.isEmpty())
                {
                    try 
                    {
                        po.createProject(po.connection.getCompanyID(username), request.getParameter("name"), request.getParameter("client"), new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startdate")), new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("enddate")));
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(Projectoverviewservlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(Projectoverviewservlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               
                //roep create aan
            }
        }

        
        if(request.getParameter("deleteimage")!=null)
        {
        
        }
        
        if(request.getParameter("importimage")!=null)
        {
        
        }
        
        if(request.getParameter("koppel")!=null)
        {
        
        }
        
        if(request.getParameter("addemail")!=null)
        {
        
        }
        
        if(request.getParameter("deleteemail")!=null)
        {
        
        }
        
        if(request.getParameter("importemail")!=null)
        {
        
        }
        
        
        
        
        
    }
    
    
    
}
