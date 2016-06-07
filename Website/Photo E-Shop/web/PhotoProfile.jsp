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
        <table style="margin: 0px; margin-top: 15px;">
        
        <%
            Database db = new Database();
            ResultSet resultSet = null;
            StringBuffer sb = new StringBuffer();
            sb.append("C:\\Temp\\");
            sb.append("test");
            String path;
            if(db.Connect())
            {
                Statement stmt = db.myConn.createStatement();
                resultSet = stmt.executeQuery("SELECT * FROM picture");
                //resultSet = db.GetQuery("SELECT * FROM picture");
            }
             while (resultSet.next())
             {
                 int count = 1;
                 sb.append(count);
                 Blob b = resultSet.getBlob(6);
                 count += 1;
                 path = sb.toString();
                 byte baa[] = b.getBytes(1, (int)b.length());
                 FileOutputStream fos=new FileOutputStream(path+".png");
                 fos.write(baa);
                 fos.close();
                 /*Blob bl = resultSet.getBlob(6);
                 byte[] pict = bl.getBytes(1,(int)bl.length());
                 response.setContentType("image/png");                 
                 OutputStream o = response.getOutputStream();*/
        %>   
        <tr>
        <td>
            <img alt="" src="<%=path+".png"%>">
            <!--<img class= "PhotoPreview" scr="</%o.write(pict);%>" />-->
        </td>    
        </tr>
        <%            
                //o.flush();
             }          
        %>   
        
        </table>
        <div class="col-md-1">
                        <input type="submit" value="SubmitButton">
                    </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
