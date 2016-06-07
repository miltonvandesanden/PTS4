<%-- 
    Document   : PhotoProfile
    Created on : 17-mei-2016, 11:58:19
    Author     : Nick
--%>
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
        <%
            Database db = new Database();
            ResultSet resultSet = null;
            if(db.Connect())
            {
                resultSet = db.GetQuery("SELECT * FROM picture");
            }
             while (resultSet.next())
             {
                 Blob bl = resultSet.getBlob("picture");          
                 byte[] pict = bl.getBytes(1,(int)bl.length());
                 /*BufferedImage img = ImageIO.read(new ByteArrayInputStream(pict));
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 ImageIO.write( img, "jpg", baos );
                 baos.flush();
                 byte[] imageInByteArray = baos.toByteArray();
                 baos.close();*/
                 //String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(pict);                 
                 System.out.println(pict.toString());
                 System.out.println(bl.toString());
                 response.setContentType("text/png");
                 OutputStream o = response.getOutputStream();
        %>
        <table style="margin: 0px; margin-top: 15px;">
        <tr>
        <td>
            <img class= "PhotoPreview" id="logo" scr="<%o.write(pict); o.flush();%>" />
        </td>
        </tr>
        </table>
        <%
            o.flush();
            }
        %>   
        
        <div class="col-md-1">
                        <input type="submit" value="Nick is Kewl">
                    </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
