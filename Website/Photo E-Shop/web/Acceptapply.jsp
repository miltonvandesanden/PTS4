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
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <h1>Accept or deny applicant</h1>
        <!--<div class="container">-->
        <div class="row col-md-12">
        <div class="col-md-1">
        <form method="post" name="form1" action="${pageContext.request.contextPath}/acceptclass">
        <TABLE id = "myTable" BORDER =1>
        <TR>
            <TH>Accept</TH>
            <TH>Rebuff</TH>
            <TH>Name</TH>
        </TR>
    
        <%
            Database db = new Database();
            ResultSet resultset = null;
            if(db.Connect())
            {
                resultset = db.GetQuery("SELECT * FROM Company WHERE IsAccepted = 0");
            }
            while (resultset.next())
            {%>
            <TR>
                <TD><input type="checkbox" name = "accept" value="<%= resultset.getInt("Companyid") %>"></TD>
                <TD><input type="checkbox" name = "decline" value="<%= resultset.getInt("Companyid") %>"></TD>
                <TD><%= resultset.getString("Name") %> </TD>                
            </TR>

        <% 
            } 
        %>
        </TABLE>
        </div>   
        </div>        
        </div>
        <input type ="submit" value="submit" name = "sendChoices"/>
        </form>
        <% 
           //response.sendRedirect("Acceptapply.jsp");
        %>
        <jsp:include page="footer.jsp"/>
    </body>
</html>