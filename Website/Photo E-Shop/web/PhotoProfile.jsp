<%-- 
    Document   : PhotoProfile
    Created on : 17-mei-2016, 11:58:19
    Author     : Nick
--%>
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
            BufferedImage img = null;
            ResultSet resultSet = null;
            StringBuffer sb = new StringBuffer(); //used to make a path
            sb.append("C:\\Temp\\");
            sb.append("test");
            String path;
            if(db.Connect())
            {
                Statement stmt = db.myConn.createStatement();
                resultSet = stmt.executeQuery("SELECT * FROM picture");                
            }
             while (resultSet.next())
             {
                 int imgId = resultSet.getInt(1);
                 int count = 1;
                 sb.append(count); //make every image have an original name
                 Blob b = resultSet.getBlob(6);
                 count += 1;
                 path = sb.toString(); //set path to temp folder
                 byte baa[] = b.getBytes(1, (int)b.length());
                 FileOutputStream fos=new FileOutputStream(path+".png"); //write image to path as png
                 fos.write(baa);
                 fos.close();
                 img = ImageIO.read(new File(path +".png")); 
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 ImageIO.write( img, "png", baos ); //write the image so it's in the baos
                 baos.flush();
                 byte[] imageInByteArray = baos.toByteArray();//load all bytes in the image
                 baos.close();
                 String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);//make a string with the bytes                 
        %>   
        <tr>
            <td>
                <input type="radio" name="selectedimage" value="<%=imgId%>">
            </td>
        <td>
            <img name="<%=imgId %>" src="data:image/jpg;base64, <%=b64%>" width="80px" height="80px" alt="Visruth.jpg not found" /><br>
            <!-- load the string and revert it to bytes -->          
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
