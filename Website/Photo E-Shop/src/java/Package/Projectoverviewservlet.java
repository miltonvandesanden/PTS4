/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import BusinessLayer.Connection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(request.getParameter("submit")!=null)
        {
            File file ;
            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;
            Database database = null;
            Connection connection = null;
            try {
                database = new Database();
                connection = new Connection();
                database.Connect();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Projectoverviewservlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String filePath = "C:\\xampp\\tomcat\\webapps\\Photo_E-Shop\\images\\";
            try
            {
                // Verify the content type
                String contentType = request.getContentType();
                if ((contentType.indexOf("multipart/form-data") >= 0)) 
                {
//                    DiskFileItemFactory factory = new DiskFileItemFactory();
//                    // maximum size that will be stored in memory
//                    factory.setSizeThreshold(maxMemSize);
//                    // Location to save data that is larger than maxMemSize.
//                    factory.setRepository(new File("C:\\xampp\\tomcat\\webapps\\Photo_E-Shop\\images\\"));
//
//                    // Create a new file upload handler
//                    ServletFileUpload upload = new ServletFileUpload((FileItemFactory) factory);
//                    // maximum file size to be uploaded.
//                    upload.setSizeMax( maxFileSize );
                    
                    try
                    { 
                        Part filePart = request.getPart("fileupload");
                       // Parse the request to get file items.
                       //List fileItems = upload.parseRequest((RequestContext) request);

                       // Process the uploaded file items
                       //Iterator i = fileItems.iterator();
              //
              //         out.println("<html>");
              //         out.println("<head>");
              //         out.println("<title>JSP File upload</title>");  
              //         out.println("</head>");
              //         out.println("<body>");
//                       while ( i.hasNext () ) 
//                       {
//                            FileItem fi = (FileItem)filePart;
//                            if ( !fi.isFormField () )	
//                            {
//                              // Get the uploaded file parameters
//                              String fieldName = fi.getFieldName();
//                              String fileName = fi.getName();
//                              boolean isInMemory = fi.isInMemory();
//                              long sizeInBytes = fi.getSize();
//                              // Write the file
//                              if( fileName.lastIndexOf("\\") >= 0 )
//                              { 
//                                file = new File( filePath + 
//                                fileName.substring( fileName.lastIndexOf("\\"))) ;
//                              }
//                              else
//                              {
//                                file = new File( filePath + 
//                                fileName.substring(fileName.lastIndexOf("\\")+1)) ;
//                              }
//                              fi.write( file ) ;
//                              BufferedImage in = ImageIO.read(file);
                              //select project id van selected project
                              //String INSERT_PICTURE = "INSERT INTO \"Picture\"(\"PictureID\", \"ProjectID\", \"Height\", \"Width\", \"colorType\", \"picture\") VALUES (PictureSequence.nextval, 1,"+ in.getHeight() + ", " + in.getWidth()  + ", 'Color', ?)";
                            String INSERT_PICTURE = "INSERT INTO \"PICTURE\"(\"PICTUREID\", \"PROJECTID\", \"HEIGHT\", \"WIDTH\", \"COLORTYPE\", \"PICTURE\") VALUES (PictureSequence.nextval, 1, 500, 500, 'Color', ?)";

                              //FileInputStream fis = null;
                              InputStream is = null;
                              PreparedStatement ps = null;
                              try 
                              {
                                //fis = new FileInputStream(file);
                                //fis = filePart.getInputStream();
                                is = filePart.getInputStream();
                                //Connection myConn = database.myConn;
                                ps = database.myConn.prepareStatement(INSERT_PICTURE);
                                //ps.setBlob(1, fis);
                                ps.setBlob(1, is);
                                ps.executeUpdate();
                                database.myConn.commit();
                              } 
                                finally 
                                {
                                    try
                                    {
                                      ps.close();
                                      //fis.close();
                                      is.close();
                                    }
                                    catch(Exception exception)
                                    {

                                    }
                                }
                                //insert ook in koppeltabel
//                                out.println("Uploaded Filename: " + filePath + 
//                                fileName + "<br>");
                            //}
                        //}
                //         out.println("</body>");
                //         out.println("</html>");
                    }
                    catch(IOException | ServletException | SQLException ex) {
                         System.out.println(ex);
                      }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Er is iets fout gegaan probeer het opnieuw");
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        
        if(request.getParameter("deleteproject")!=null)
        {
        
        }
        String p =request.getParameter("openproject");
        if(request.getParameter("openproject")!=null)
        {

        }
        
        if(request.getParameter("Save")!=null)
        {
        
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
