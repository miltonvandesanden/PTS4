<%-- 
    Document   : editPhoto
    Created on : 14-jun-2016, 12:54:05
    Author     : Nick
--%>

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
        %>
            
        <input type="checkbox" checked="true" />
        <input type="button" value="gelukt" />
            <!-- load the string and revert it to bytes -->          
            
        <% 
        }
        %>
        
        

       
       <jsp:include page="footer.jsp"/>
    </body>
</html>
