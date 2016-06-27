<%-- 
    Document   : Acceptapply
    Created on : 26-apr-2016, 9:42:03
    Author     : Danny
--%>

<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.MessagingException"%>
<%@page import="javax.mail.PasswordAuthentication"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Session"%>
<%@page import="java.util.Properties"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Package.Database"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.ArrayList"%><%@page import="BusinessLayer.Connection"%>
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
        <title>JSP Page</title>
        <link rel="stylesheet" href="bootstrap.css"/>
<!--    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/t/dt/dt-1.10.11/datatables.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#myTable").dataTable();
            });
        </script>
-->
    </head>
    <body> 
        <jsp:include page="header.jsp"/>
        <div id="content" class="row col-md-12">
        <h1><fmt:message key="acceptApply.label.head" /></h1>
        <!--<div class="container">-->
        <div class="row col-md-12">
        <div class="col-md-1">
        <form method="post" name="form1" action="${pageContext.request.contextPath}/acceptclass">
        <TABLE id = "myTable" BORDER =1>
        <TR>
            <TH><fmt:message key="acceptApply.label.accept" /></TH>
            <TH><fmt:message key="acceptApply.label.rebuff" /></TH>
            <TH><fmt:message key="acceptApply.label.name" /></TH>
            <TH><fmt:message key="acceptApply.label.email" /></TH>
        </TR>
    
        <%
            Database db = new Database();
            ResultSet resultset = null;
            if(db.Connect())
            {
                resultset = db.GetQuery("SELECT u.EMAIL,c.\"Name\" FROM \"User\" u, \"COMPANY\" c WHERE u.USERID = c.Userid AND c.ISACCEPTED = 0");
                //"SELECT * FROM Company WHERE IsAccepted = 0");
            }
            while (resultset.next())
            {%>
            <TR>
                <TD><input type="checkbox" name = "accept" value="<%= resultset.getString("EMAIL") %>"></TD>
                <TD><input type="checkbox" name = "decline" value="<%= resultset.getString("EMAIL") %>"></TD>
                <TD><%= resultset.getString("Name") %> </TD> 
                <TD><%= resultset.getString("EMAIL") %> </TD> 
            </TR>

        <% 
            } 
        %>
        </TABLE>
        </div>   
        </div>        
        </div>
        <input type ="submit" value="<fmt:message key="acceptApply.button.submit" />" name = "sendChoices"/>
        </form>
        <% 
           //response.sendRedirect("Acceptapply.jsp");
        %>
        <jsp:include page="footer.jsp"/>
    </body>
</html>