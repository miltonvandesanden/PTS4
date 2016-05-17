<%-- 
    Document   : PhotoProfile
    Created on : 17-mei-2016, 11:58:19
    Author     : Nick
--%>
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
        String[] columnNames = {"First Name",
                        "Last Name",
                        "Sport",
                        "# of Years",
                        "Vegetarian"};
        Object[][] data = {
    {"Kathy", "Smith",
     "Snowboarding", new Integer(5), new Boolean(false)},
    {"John", "Doe",
     "Rowing", new Integer(3), new Boolean(true)},
    {"Sue", "Black",
     "Knitting", new Integer(2), new Boolean(false)},
    {"Jane", "White",
     "Speed reading", new Integer(20), new Boolean(true)},
    {"Joe", "Brown",
     "Pool", new Integer(10), new Boolean(false)}};
    JTable table = new JTable(data, columnNames);%>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
