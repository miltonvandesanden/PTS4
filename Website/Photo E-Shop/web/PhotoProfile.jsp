<%-- 
    Document   : PhotoProfile
    Created on : 17-mei-2016, 11:58:19
    Author     : Nick
--%>
<%@page import="BusinessLayer.PhotoProfile"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Package.Database"%>
<%@page import="javax.swing.JTable"%>
<%@page import="BusinessLayer.Connection"%>
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
        <form method="post" action="${pageContext.request.contextPath}/PhotoProfileServlet">
        <table style="margin: 0px; margin-top: 15px;">        
        <%
            Database db = new Database();            
            ResultSet resultSet = null;
            PhotoProfile photo = new PhotoProfile();
            if(db.Connect())
            {
                Statement stmt = db.myConn.createStatement();
                resultSet = stmt.executeQuery("SELECT * FROM picture");                
            }
             while (resultSet.next())
             {
                 String b64 = photo.GetAllImages(resultSet);
                 int imgId = resultSet.getInt(1);                             
        %>   
        <tr id="afstand">
            <td>
                <input type="radio" name="selectedimage" value="<%=imgId%>">
            </td>
        <td >
            <img  name="<%=imgId %>" src="data:image/jpg;base64, <%=b64%>" width="80px" height="80px" alt="Visruth.jpg not found" /><br>
            <!-- load the string and revert it to bytes -->      
            <br/>
        </td> 
        </tr>        
        <%        
             }          
        %>        
        </table>
        <div class="col-md-1">
                        <input name="submitButton" type="submit" value="Edit!">
                    </div>
        </form>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
