<%-- 
    Document   : projectoverview
    Created on : 5-apr-2016, 10:52:37
    Author     : Stefan
--%>


<%@page import="BusinessLayer.Connection"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <%
            
        %>
        <div class="row col-md-6">
            <%
                Connection connection = new Connection();
            %>
        </div>
        <div id="content" class="row col-md-12">
            Select a file to upload: <br />
            <form action="projectoverview.jsp" method="post" enctype="multipart/form-data">
            <input type="file" name="file" size="50" />
            <br />
            <input type="submit"  name="submit"  value="Upload File" />
            <%
               if ("POST".equalsIgnoreCase(request.getMethod())) {
               File file ;
               int maxFileSize = 5000 * 1024;
               int maxMemSize = 5000 * 1024;
               ServletContext context = pageContext.getServletContext();
               String filePath = "C:\\xampp\\tomcat\\webapps\\Photo_E-Shop\\images\\";

               // Verify the content type
               String contentType = request.getContentType();
               if ((contentType.indexOf("multipart/form-data") >= 0)) {

                  DiskFileItemFactory factory = new DiskFileItemFactory();
                  // maximum size that will be stored in memory
                  factory.setSizeThreshold(maxMemSize);
                  // Location to save data that is larger than maxMemSize.
                  factory.setRepository(new File("C:\\xampp\\tomcat\\webapps\\Photo_E-Shop\\images\\"));

                  // Create a new file upload handler
                  ServletFileUpload upload = new ServletFileUpload(factory);
                  // maximum file size to be uploaded.
                  upload.setSizeMax( maxFileSize );
                  try{ 
                     // Parse the request to get file items.
                     List fileItems = upload.parseRequest(request);

                     // Process the uploaded file items
                     Iterator i = fileItems.iterator();
            //
            //         out.println("<html>");
            //         out.println("<head>");
            //         out.println("<title>JSP File upload</title>");  
            //         out.println("</head>");
            //         out.println("<body>");
                     while ( i.hasNext () ) 
                     {
                        FileItem fi = (FileItem)i.next();
                        if ( !fi.isFormField () )	
                        {
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();
                        // Write the file
                        if( fileName.lastIndexOf("\\") >= 0 ){ 
                        file = new File( filePath + 
                        fileName.substring( fileName.lastIndexOf("\\"))) ;
                        }else{
                        file = new File( filePath + 
                        fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                        }
                        fi.write( file ) ;
                        out.println("Uploaded Filename: " + filePath + 
                        fileName + "<br>");
                        }
                     }
            //         out.println("</body>");
            //         out.println("</html>");
                  }catch(Exception ex) {
                     System.out.println(ex);
                  }
               }else{
            //      out.println("<html>");
            //      out.println("<head>");
            //      out.println("<title>Servlet upload</title>");  
            //      out.println("</head>");
            //      out.println("<body>");
            //      out.println("<p>No file uploaded</p>"); 
            //      out.println("</body>");
            //      out.println("</html>");
               }
                }
            %>            
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
