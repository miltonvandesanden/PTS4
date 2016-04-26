<%-- 
    Document   : Acceptapply
    Created on : 26-apr-2016, 9:42:03
    Author     : Danny
--%>

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
        <form name="form1">
        <TABLE id = "myTable" BORDER =1>
        <TR>
            <TH>Check</TH>
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
                <TD><input type="checkbox" name = "choice" value="<%= resultset.getInt("Companyid") %>"></TD>
                <TD><%= resultset.getString("Name") %> </TD>                
            </TR>

        <% 
            } 
        %>
        </TABLE>
        </div>   
        </div>        
        </div>
        <input type ="submit" value="submit"/>
        </form>
        <% String[] results = request.getParameterValues("choice");
           if(results != null)
           {
               for(String r : results)
               {
                   String query = "UPDATE COMPANY SET IsAccepted = 1 WHERE CompanyID ="+r;
                   db.InsertQuery(query);
               }
           }
        %>
        <jsp:include page="footer.jsp"/>
    </body>
</html>