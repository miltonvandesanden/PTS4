<%-- 
    Document   : editPhoto
    Created on : 14-jun-2016, 12:54:05
    Author     : Nick
--%>

<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.Statement"%>
<%@page import="BusinessLayer.PhotoProfile"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Package.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-Copy</title>
        <link rel="stylesheet" href="bootstrap.css"/>
    </head>
    <body>
    <jsp:include page="header.jsp"/>
        <% 
        if(request.getAttribute("value") != null)
        {
            Database db = new Database();
            ResultSet resultSet = null;
            BufferedImage img = null;
            PhotoProfile photo = new PhotoProfile();
            
            if(db.Connect())
            {
                Statement stmt = db.myConn.createStatement();
                resultSet = stmt.executeQuery("SELECT * FROM picture where pictureid =" + request.getAttribute("value"));           
            }
            if (resultSet.next())
            {
                String b64 = photo.GetAllImages(resultSet);
                int imgId = resultSet.getInt(1);
                img = photo.GetSingleImage(resultSet);
                String b65 = photo.GetBlackAndWhite(resultSet);                
        %>            
            <img  name="<%=imgId %>" src="data:image/jpg;base64, <%=b65%>" width="80px" height="80px" alt="Visruth.jpg not found" />     
            Height: <%=img.getHeight() %>
            Width: <%=img.getWidth() %>           
        <% 
            }
        }
        %>     
       <jsp:include page="footer.jsp"/>
    </body>
</html>
