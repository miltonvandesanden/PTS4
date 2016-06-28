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
<%@page import="BusinessLayer.Connection"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Languages.Language" />
<!DOCTYPE html>
<html lang="${language}">
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
            <fmt:message key="editPhoto.label.heigth" />: <%=img.getHeight() %>
            <fmt:message key="editPhoto.label.width" />: <%=img.getWidth() %>           
        <% 
            }
        }
        %>     
       <jsp:include page="footer.jsp"/>
    </body>
</html>
